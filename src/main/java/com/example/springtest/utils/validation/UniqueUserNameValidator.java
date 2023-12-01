package com.example.springtest.utils.validation;

import com.example.springtest.repositories.UserRepository;
import com.example.springtest.utils.validation.annotations.UniqueUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    private final UserRepository userRepository;

    @Autowired
    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsername(s).isEmpty();
    }
}
