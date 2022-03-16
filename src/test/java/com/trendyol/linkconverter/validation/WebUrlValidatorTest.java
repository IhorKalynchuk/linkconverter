package com.trendyol.linkconverter.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WebUrlValidatorTest {
  WebUrlValidator webUrlValidator = new WebUrlValidator();

  @ParameterizedTest
  @MethodSource
  void should_convertToDeeplink(String webUrl, boolean isValid) {

    boolean result = webUrlValidator.isValid(webUrl, null);
    assertThat(result).isEqualTo(isValid);
  }

  static Stream<Arguments> should_convertToDeeplink() {
    return Stream.of(
        Arguments.of("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064", true),
        Arguments.of("https://www.trendyaal.com/casio/erkek-koi-ssti-p-1925865?boutiqueId=439892", false),
        Arguments.of("", false),
        Arguments.of(null, false)
    );
  }

}