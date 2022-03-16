package com.trendyol.linkconverter.validation;

import com.trendyol.linkconverter.validation.annotation.Deeplink;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DeeplinkValidator implements ConstraintValidator<Deeplink, String> {

  private static final String PREFIX = "ty://?Page=";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return value.startsWith(PREFIX);
  }
}