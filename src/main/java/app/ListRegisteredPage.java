package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list_registered")
public class ListRegisteredPage extends HttpServlet {

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();

        Class<?>[] knownClasses = { School.class, Person.class };
        
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>About Us - Training Academy</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 15px; background: white; border-radius: 5px; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head>");

        writer.println("<body>");

// Header
        writer.println("<header>");
        writer.println("<h1>About COHORT 12 Training PORTA</h1>");
        writer.println("</header>");

        for (Class<?> clazz : knownClasses) {
            String dbKey = clazz.getSimpleName().toUpperCase() + "_DB";
            List<?> register;
            if (session.getAttribute(dbKey) == null)
                register = new ArrayList<>();
            else
                register = (List<?>) session.getAttribute(dbKey);

            Cohort12Framework.htmlTable(writer, clazz, register);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("footer");
        dispatcher.include(req, resp);

        writer.println("</body>");
        writer.println("</html>");

    }
}
