package com.example.springtest.utils.validation;

import com.example.springtest.repositories.ModelRepository;
import com.example.springtest.utils.validation.annotations.UniqueModelName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueModelNameValidator implements ConstraintValidator<UniqueModelName, String> {
    private final ModelRepository modelRepository;

    @Autowired
    public UniqueModelNameValidator(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return modelRepository.findByName(s).isEmpty();
    }
}
