package com.hire.validator.custom.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hire.dto.UserDTO;
import com.hire.validator.custom.api.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {  
	
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {  
    	
    }
    
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){   
        UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());    
    }     
}
