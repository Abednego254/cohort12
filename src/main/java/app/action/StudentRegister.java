package app.action;

import app.model.Student;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Register Student",
        urlPatterns = { "/register_student" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Cohort 12"),
                @WebInitParam(name = "pageHeader", value = "Student Registration")
        })
public class StudentRegister extends BaseAction<Student> {
}
