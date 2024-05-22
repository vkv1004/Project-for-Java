package com.webapp.phystechschool.annotation;

import com.webapp.phystechschool.validation.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Пожалуйста, выберите надёжный пароль";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
