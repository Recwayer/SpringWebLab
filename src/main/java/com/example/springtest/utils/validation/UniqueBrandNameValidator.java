package com.example.springtest.utils.validation;

import com.example.springtest.repositories.BrandRepository;
import com.example.springtest.utils.validation.annotations.UniqueBrandName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {
    private BrandRepository brandRepository;

    public UniqueBrandNameValidator(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return brandRepository.findByName(s).isEmpty();
    }
}
