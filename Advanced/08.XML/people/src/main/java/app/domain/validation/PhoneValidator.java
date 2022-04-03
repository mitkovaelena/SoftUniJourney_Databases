package app.domain.validation;

import app.domain.model.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    public void initialize(Phone constraint) { ;
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return obj != null && obj.matches("[0-9]{8,10}");
    }
}
