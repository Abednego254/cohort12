package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class CohortServeltAction<T> extends HttpServlet {

    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("SESSION_ID") == null) {
            req.getSession().invalidate();
            resp.sendRedirect("./login");
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Register</title>");
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
        writer.println("<h1>Registration Form</h1>");
        writer.print("Logged In User: ");
        writer.println(session.getAttribute("UserActualName"));
        writer.println("</header>");

        writer.println("<section>");
        Cohort12Framework.htmlForm(writer, getEntityClass());
        writer.println("</section>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("footer");
        dispatcher.include(req, resp);

        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Class<T> clazz = getEntityClass();

        String dbKey = clazz.getSimpleName().toUpperCase() + "_DB";

        List<T> register;
        if (session.getAttribute(dbKey) == null)
            register = new ArrayList<>();
        else
            register = (List<T>) session.getAttribute(dbKey);

        try {
            T entity = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.populate(entity, req.getParameterMap());
            register.add(entity);
            session.setAttribute(dbKey, register);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Cohort12Table tableAnnot = clazz.getAnnotation(Cohort12Table.class);
        String redirectUrl = (tableAnnot != null) ? tableAnnot.tableUrl() : "./list_registered";
        resp.sendRedirect(redirectUrl);
    }
}
