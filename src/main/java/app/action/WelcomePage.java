package app.action;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WelcomePage",
    urlPatterns = { "/" }
)
public class WelcomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Training Portal</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 15px; background: white; border-radius: 5px; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<header>");
        writer.println("<h1>Welcome to COHORT 12 Training PORTAL</h1>");
        writer.println("<p>Empowering Developers with Real-World Skills</p>");
        writer.println("</header>");

        writer.println("<section>");
        writer.println("<h2>Available Courses</h2>");
        writer.println("<ul>");
        writer.println("<li>Java Development</li>");
        writer.println("<li>QA</li>");
        writer.println("<li>Infrastructure</li>");
        writer.println("<li>Database Administration</li>");
        writer.println("</ul>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<h2>About Our Training</h2>");
        writer.println("<p>Our training programs focus on hands-on experience and real-world projects.</p>");
        writer.println("<p>We help developers build strong backend systems using modern technologies.</p>");
        writer.println("<p>Mentorship and practical coding sessions are at the core of our learning approach.</p>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<h2>Upcoming Schedule</h2>");
        writer.println("<p>Weekday Classes: 6:00 PM - 8:00 PM</p>");
        writer.println("<p>Weekend Bootcamps: 9:00 AM - 1:00 PM</p>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<a href=\"./login\">Login</a>");
        writer.println("</section>");

        writer.println("</body>");
        writer.println("</html>");

    }
}
