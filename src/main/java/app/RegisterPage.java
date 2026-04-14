package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "Register",
    urlPatterns = { "/register" },
    initParams = {
        @WebInitParam(name = "pageName", value = "Register - Training Academy"),
        @WebInitParam(name = "pageHeader", value = "Training Registration - IT")
    })
public class RegisterPage extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{

        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();
        System.out.println("session.getAttribute(\"SESSION_ID\"): " + session.getAttribute("SESSION_ID"));
        if(session.getAttribute("SESSION_ID") == null){
            req.getSession().invalidate();
            resp.sendRedirect("./login");
        }

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

// Header
        writer.println("<header>");
        writer.println("<h1>");
        writer.println(config.getInitParameter("pageHeader"));
        writer.print("Logged In User: ");
        writer.println(session.getAttribute("UserActualName"));
        writer.println("</h1>");
        writer.println("</header>");

// Form
        writer.println("<section>");
        Cohort12Framework.htmlForm(writer, School.class);
        writer.println("</section>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("footer");
        dispatcher.include(req, resp);

        writer.println("</body>");
        writer.println("</html>");

    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();

        List<School> register;
        if (session.getAttribute("PERSONS_DB") == null)
            register = new ArrayList<>();
        else
            register = (List<School>) session.getAttribute("PERSONS_DB");

        School school = new School();
        try {
            BeanUtils.populate(school, req.getParameterMap());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        register.add(school);

        session.setAttribute("PERSONS_DB", register);

        resp.sendRedirect("./list_registered");
    }
}
