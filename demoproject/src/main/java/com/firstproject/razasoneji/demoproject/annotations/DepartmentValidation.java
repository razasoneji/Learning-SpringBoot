package com.firstproject.razasoneji.demoproject.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DepartmentValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface DepartmentValidation {
    String message() default "Department can be CE , IT , EC Only.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
