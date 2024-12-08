package com.firstproject.razasoneji.demoproject.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<GenderValidation,String> {

    private final ArrayList<String> validGenders;

    @Autowired
    public GenderValidator(ArrayList<String> validGenders) {
        this.validGenders = validGenders;
    }


    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        for(String gender : validGenders){
            if(gender.equals(input)){
                return true;
            }
        }
        return false;
    }
}
