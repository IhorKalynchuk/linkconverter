package com.trendyol.linkconverter.validation.annotation;

import com.trendyol.linkconverter.validation.WebUrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
    ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WebUrlValidator.class)
@Documented
public @interface WebUrl {
  String message() default "must be a valid Web URL, but found: ${validatedValue}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
