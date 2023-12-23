package com.ctseducare.moviebook.validation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class ValidationErrorHelper {
	
    public static <T> ValidationErrorResponse createFrom(Set<ConstraintViolation<T>> violations) {
        List<FieldError> errors = violations
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        return new ValidationErrorResponse("Validation Error", errors);
    }

}
