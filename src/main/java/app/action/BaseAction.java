package app.action;

import app.framework.Cohort12Framework;
import app.framework.Cohort12Table;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseAction<T> extends HttpServlet {

    @SuppressWarnings("unchecked")
    public T serializeForm(Map<String, String[]> requestMap) {

        System.out.println("Form Serialization....");

        try {
            T clazzInstance = this.getType().getDeclaredConstructor().newInstance();

            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
                @Override
                public Object convert(String value, Class clazz) {
                    if (clazz.isEnum()) {
                        return Enum.valueOf(clazz, value);
                    } else if (clazz == Date.class) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            return dateFormat.parse(value);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        return super.convert(value, clazz);
                    }
                }
            });

            ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);

            beanUtilsBean.populate(clazzInstance, requestMap);

            return clazzInstance;

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e ) {
            throw new RuntimeException(e);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        java.sql.Connection conn = (java.sql.Connection) getServletContext().getAttribute("dbConnection");

        try {
            T entity = this.serializeForm(req.getParameterMap());
            saveToDb(conn, entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (this.getType().isAnnotationPresent(Cohort12Table.class)) {
            resp.sendRedirect(this.getType()
                .getAnnotation(Cohort12Table.class).tableUrl());

        } else {
            resp.sendRedirect("./home");
        }
    }

    private void saveToDb(java.sql.Connection conn, T entity) throws Exception {
        Class<?> clazz = entity.getClass();
        String tableName = clazz.getSimpleName().toLowerCase();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder("VALUES (");
        
        java.util.List<Object> params = new java.util.ArrayList<>();
        
        for (java.lang.reflect.Field field : fields) {
            if (field.isAnnotationPresent(app.framework.Cohort12FormField.class)) {
                field.setAccessible(true);
                sql.append(field.getName()).append(",");
                values.append("?,");
                params.add(field.get(entity));
            }
        }
        
        if (params.isEmpty()) return;
        
        sql.setLength(sql.length() - 1);
        values.setLength(values.length() - 1);
        sql.append(") ").append(values).append(")");
        
        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            pstmt.executeUpdate();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{

        HttpSession session = req.getSession();
        ServletConfig config = getServletConfig();

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>");
        writer.println(config.getInitParameter("pageName"));
        writer.println("</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; max-width: 400px; }");
        writer.println("input { width: 100%; padding: 8px; margin: 10px 0; }");
        writer.println("button { padding: 10px; background-color: #3498db; color: white; border: none; width: 100%; }");
        writer.println("a { display: inline-block; margin-top: 10px; color: #3498db; }");
        writer.println("</style>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<header>");
        writer.println("<h1>");
        writer.println(config.getInitParameter("pageHeader"));
        writer.print("Logged In User: ");
        writer.println(session.getAttribute("UserActualName"));
        writer.println("</h1>");
        writer.println("</header>");

        writer.println("<section>");
        Cohort12Framework.htmlForm(writer, this.getType());
        writer.println("</section>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("footer");
        dispatcher.include(req, resp);

        writer.println("</body>");
        writer.println("</html>");

    }

    @SuppressWarnings("unchecked")
    public Class<T> getType() {
        ParameterizedType superClass =
            (ParameterizedType) getClass().getGenericSuperclass();

        return (Class<T>) superClass.getActualTypeArguments()[0];
    }

    public String dbName(){
        return this.getType().getSimpleName().toUpperCase() + "_DB";
    }

    public List<T> returnData(HttpSession session){
        java.sql.Connection conn = (java.sql.Connection) session.getServletContext().getAttribute("dbConnection");
        List<T> result = new ArrayList<>();
        Class<T> clazz = this.getType();
        String tableName = clazz.getSimpleName().toLowerCase();
        
        String sql = "SELECT * FROM " + tableName;
        try (java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
             
             while (rs.next()) {
                 T instance = clazz.getDeclaredConstructor().newInstance();
                 java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
                 for (java.lang.reflect.Field field : fields) {
                     if (field.isAnnotationPresent(app.framework.Cohort12FormField.class)) {
                         field.setAccessible(true);
                         Object value = rs.getObject(field.getName());
                         if (value != null) {
                             org.apache.commons.beanutils.BeanUtils.setProperty(instance, field.getName(), value);
                         }
                     }
                 }
                 result.add(instance);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
