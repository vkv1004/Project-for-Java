package com.webapp.phystechschool.validation;

import com.webapp.phystechschool.annotation.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class PasswordStrengthValidatorTest {

    private PasswordStrengthValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setup() {
        validator = new PasswordStrengthValidator();
        PasswordValidator annotation = mock(PasswordValidator.class);
        context = mock(ConstraintValidatorContext.class);

        validator.initialize(annotation);
    }

    @Test
    public void testIsValid_ReturnsTrueForStrongPassword() {
        String strongPassword = "StrongPassword123";

        assertTrue(validator.isValid(strongPassword, context));
    }

    @Test
    public void testIsValid_ReturnsFalseForWeakPassword() {
        String weakPassword = "password";

        assertFalse(validator.isValid(weakPassword, context));
    }

    @Test
    public void testIsValid_ReturnsTrueForNewWeakPassword() {
        String newWeakPassword = "abc123";

        assertFalse(validator.isValid(newWeakPassword, context));
    }

    @Test
    public void testIsValid_ReturnsTrueForEmptyPassword() {
        String emptyPassword = "";

        assertTrue(validator.isValid(emptyPassword, context));
    }
}
