package app.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraint) {

    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return obj != null && obj.matches("[0-9]{8,10}");
    }
}
