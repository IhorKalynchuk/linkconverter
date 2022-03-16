package com.trendyol.linkconverter.validation;

import com.trendyol.linkconverter.validation.annotation.WebUrl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WebUrlValidator implements ConstraintValidator<WebUrl, String> {

  private static final String PREFIX = "https://www.trendyol.com/";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return value.startsWith(PREFIX);
  }
}