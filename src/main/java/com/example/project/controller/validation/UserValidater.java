package com.example.project.controller.validation;

import com.example.project.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidater implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
        //item ==class
        //sub item == class 자식이어도 통과되어서 좋음
    }

    @Override
    public void validate(Object target, Errors errors) {
        //error의 자식이 bindingresult
        User user = (User) target;



    }
}
