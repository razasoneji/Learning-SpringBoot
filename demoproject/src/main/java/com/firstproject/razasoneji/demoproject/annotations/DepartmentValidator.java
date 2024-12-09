package com.firstproject.razasoneji.demoproject.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentValidator implements ConstraintValidator <DepartmentValidation,String> {

    @Override
    public void initialize(DepartmentValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null||s.equals("")){
        return false;
        }
        if(s.equalsIgnoreCase("CE")||s.equalsIgnoreCase("IT"||s.equalsIgnoreCase("EC"))){
            return true;
        }
        return false;
    }
}
