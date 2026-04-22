package app.action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Home - Cohort 12</title>");

        out.println("<style>");
        out.println("body { margin: 0; font-family: Arial, sans-serif; background-color: #f4f6f8; }");
        out.println(".navbar { display: flex; justify-content: space-between; align-items: center; background-color: #2c3e50; padding: 0 20px; }");
        out.println(".logo { color: white; font-size: 20px; font-weight: bold; }");
        out.println(".nav-links { display: flex; }");
        out.println(".nav-links a { color: white; text-decoration: none; padding: 15px 20px; transition: background 0.3s; }");
        out.println(".nav-links a:hover { background-color: #34495e; }");
        out.println(".container { padding: 40px; }");
        out.println(".card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 20px; }");
        out.println("h2 { margin-top: 0; }");
        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");
        out.println("  <div class='logo'>COHORT 12</div>");
        out.println("  <div class='nav-links'>");
        out.println("      <a href='./home'>Home</a>");
        out.println("      <a href='./register_school'>Register School</a>");
        out.println("      <a href='./register_person'>Register Person</a>");
        out.println("      <a href='./register_student'>Register Student</a>");
        out.println("      <a href='./register_course'>Register Course</a>");
        out.println("      <a href='./register_trainer'>Register Trainer</a>");
        out.println("      <a href='./person_lists'>Person List</a>");
        out.println("      <a href='./school_lists'>School List</a>");
        out.println("      <a href='./student_lists'>Student List</a>");
        out.println("      <a href='./course_lists'>Course List</a>");
        out.println("      <a href='./trainer_lists'>Trainer List</a>");
        out.println("      <a href='./login'>Logout</a>");
        out.println("  </div>");
        out.println("</div>");

        out.println("<div class='container'>");
        out.println("  <div class='card'>");
        out.println("    <h2>Welcome to the Training Portal</h2>");
        out.println("    <p>Manage your registrations and view reports from the navigation menu above.</p>");
        out.println("  </div>");
        out.println("</div>");

        // Include the footer
        RequestDispatcher dispatcher = request.getRequestDispatcher("/footer");
        dispatcher.include(request, response);

        out.println("</body>");
        out.println("</html>");
    }
}
