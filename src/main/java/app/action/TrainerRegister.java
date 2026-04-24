package app.action;

import app.model.Trainer;
import app.utility.validation.Validate;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

/**
 * TrainerRegister Servlet
 * DEMONSTRATION: This servlet has NO LOGIC written inside it.
 * All doGet/doPost functionality is inherited from BaseAction<T> using Reflection.
 */
@WebServlet(name = "Register Trainer",
        urlPatterns = { "/register_trainer" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Trainer Registration"),
                @WebInitParam(name = "pageHeader", value = "Trainer Enrollment")
        })
public class TrainerRegister extends BaseAction<Trainer> {
    // Look trainer, no body! All logic is in the generic framework.

    @Inject
    Validate validate;
}
