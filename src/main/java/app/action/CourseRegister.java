package app.action;

import app.model.Course;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Register Course",
        urlPatterns = { "/register_course" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Course"),
                @WebInitParam(name = "pageHeader", value = "Course Registration")
        })
public class CourseRegister extends BaseAction<Course> {
}
