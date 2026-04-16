package app.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/footer")
public class FooterPage extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        // Navigation
        writer.println("<section>");
        writer.println("<a href='./home'>&larr; Back to Home</a> | ");
        writer.println("<a href='./login'>Log Out</a>");
        writer.println("</section>");

    }
}
