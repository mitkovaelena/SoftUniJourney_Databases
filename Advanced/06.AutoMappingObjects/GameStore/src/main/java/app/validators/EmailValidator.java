package app.validators;

import app.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.matches("^[A-Za-z0-9][A-Za-z0-9.-_]*[A-Za-z0-9]@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\\w+$");
    }
}
