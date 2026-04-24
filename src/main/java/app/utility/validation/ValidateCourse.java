package app.utility.validation;

import jakarta.enterprise.context.ApplicationScoped;

@ValidatorQualifier(ValidatorQualifier.ValidationChoice.COURSE)
@ApplicationScoped
public class ValidateCourse implements Validate {
    public boolean name(String name){
        return name != null && name.length() > 2;
    }
}
