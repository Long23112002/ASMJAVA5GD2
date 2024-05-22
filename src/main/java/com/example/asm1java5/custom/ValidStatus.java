package com.example.asm1java5.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatus {
    String message() default "Status must be 0 or 1 or 2";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
