package app;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Register School",
    urlPatterns = { "/register_school" },
    initParams = {
            @WebInitParam(name = "pageName", value = "Register - Training Academy"),
            @WebInitParam(name = "pageHeader", value = "Training Registration - IT")
    })
public class SchoolRegister extends CohortServeltAction<School>{

}
