package com.example.springtest.utils.validation.annotations;

import com.example.springtest.utils.validation.UniqueBrandNameValidator;
import com.example.springtest.utils.validation.UniqueModelNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueModelNameValidator.class)
public @interface UniqueModelName {
    String message() default "Model already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
