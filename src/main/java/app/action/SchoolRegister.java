package app.action;

import app.model.School;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Register School",
        urlPatterns = { "/register_school" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Cohort 12"),
                @WebInitParam(name = "pageHeader", value = "School Registration")
        })
public class SchoolRegister extends BaseAction<School> {
}
