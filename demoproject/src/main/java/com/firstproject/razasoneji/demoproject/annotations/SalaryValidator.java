package com.firstproject.razasoneji.demoproject.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SalaryValidator implements ConstraintValidator<SalaryValidation,Long>{

    @Override
    public void initialize(SalaryValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        //String.valueOf(Math.abs(number)).length();
        int digitsOfNumber = String.valueOf(aLong).length();
        return digitsOfNumber <= 9 && digitsOfNumber >= 6;
    }
}
