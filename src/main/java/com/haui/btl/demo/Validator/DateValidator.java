package com.haui.btl.demo.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<CusValidDate, LocalDateTime> {
    private boolean past;
    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime now = LocalDateTime.now();
        if(!localDateTime.isBefore(now)){
            return true;
        }
        return false;
    }

    @Override
    public void initialize(CusValidDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        past = constraintAnnotation.past();
    }
}
