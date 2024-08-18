package com.haui.btl.demo.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DateValidator.class}
)
public @interface CusValidDate {
    String message() default "Bạn không thể nhập ngày trong quá khứ!";

    boolean past();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
