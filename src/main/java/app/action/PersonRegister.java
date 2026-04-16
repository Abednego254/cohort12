package app.action;

import app.model.Person;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "Register Person",
        urlPatterns = { "/register_person" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Cohort 12"),
                @WebInitParam(name = "pageHeader", value = "Person Registration")
        })
public class PersonRegister extends BaseAction<Person> {
}
