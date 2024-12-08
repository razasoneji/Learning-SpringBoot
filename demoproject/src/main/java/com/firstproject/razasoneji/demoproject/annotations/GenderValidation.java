package com.firstproject.razasoneji.demoproject.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //actual runtime running one.
@Target({ElementType.FIELD, ElementType.PARAMETER}) // targets which value , field/attribute or method , ie annotation on whom
@Constraint(validatedBy = { GenderValidator.class })
public @interface GenderValidation {

    String message() default "Gender can be either male, female";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
