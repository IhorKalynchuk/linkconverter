package com.trendyol.linkconverter.converter.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SearchConverterTest {
  SearchConverter searchConverter = new SearchConverter();

  @ParameterizedTest
  @MethodSource
  void should_convertToDeeplink(String webUrl, String deeplink) {

    String result = searchConverter.convertToDeeplink(webUrl);
    assertThat(result).isEqualTo(deeplink);
  }

  static Stream<Arguments> should_convertToDeeplink() {
    return Stream.of(
        Arguments.of("https://www.trendyol.com/sr?q=elbise", "ty://?Page=Search&Query=elbise"),
        Arguments.of("https://www.trendyol.com/sr?q=%C3%BcCt%C3%BC", "ty://?Page=Search&Query=%C3%BcCt%C3%BC")
    );
  }

}