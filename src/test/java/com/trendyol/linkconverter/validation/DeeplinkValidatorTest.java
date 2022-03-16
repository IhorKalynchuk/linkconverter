package com.trendyol.linkconverter.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DeeplinkValidatorTest {
  private DeeplinkValidator deeplinkValidator = new DeeplinkValidator();

  @ParameterizedTest
  @MethodSource
  void should_validate(String deeplink, boolean isValid) {

    boolean result = deeplinkValidator.isValid(deeplink, null);
    assertThat(result).isEqualTo(isValid);
  }

  static Stream<Arguments> should_validate() {
    return Stream.of(
        Arguments.of("ty://?Page=Product&ContentId=1925865&MerchantId=105064", true),
        Arguments.of("ty://?Paage=Product&ContentId=1925865&MerchantId=105064", false),
        Arguments.of("https://www.trendyaal.com/casio/erkek-koi-ssti-p-1925865?boutiqueId=439892", false),
        Arguments.of("", false),
        Arguments.of(null, false)
    );
  }
}