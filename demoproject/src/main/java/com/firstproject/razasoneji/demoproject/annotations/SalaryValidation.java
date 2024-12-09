package com.firstproject.razasoneji.demoproject.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = {SalaryValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface SalaryValidation
{
    String message() default "Salary Cannot be more than 9 figures and less than 6 figures ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
