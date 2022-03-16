package com.trendyol.linkconverter.converter.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

  @ParameterizedTest
  @MethodSource
  void should_convertToWebUrl(String deeplink, String webUrl) {

    String result = searchConverter.convertToWebUrl(deeplink);
    assertThat(result).isEqualTo(webUrl);
  }

  static Stream<Arguments> should_convertToWebUrl() {
    return Stream.of(
        Arguments.of("ty://?Page=Search&Query=elbise", "https://www.trendyol.com/sr?q=elbise"),
        Arguments.of("ty://?Page=Search&Query=%C3%BCt%C3%BC", "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC")
    );
  }

  @ParameterizedTest
  @MethodSource
  void should_checkIfWebSearch(String webUrl, boolean expected) {

    Predicate<String> predicate = searchConverter.isWebUrl();
    boolean result = predicate.test(webUrl);
    assertThat(expected).isEqualTo(result);
  }

  static Stream<Arguments> should_checkIfWebSearch() {
    return Stream.of(
        Arguments.of("https://www.trendyol.com/sr?q=elbise", true),
        Arguments.of("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC", true),
        Arguments.of("https://www.trendyol.com/search?q=%C3%BCt%C3%BC", false)
    );
  }
  @ParameterizedTest
  @MethodSource

  void should_checkIfDeeplinkSearch(String deeplink, boolean expected) {

    Predicate<String> predicate = searchConverter.isDeeplink();
    boolean result = predicate.test(deeplink);
    assertThat(expected).isEqualTo(result);
  }

  static Stream<Arguments> should_checkIfDeeplinkSearch() {
    return Stream.of(
        Arguments.of("ty://?Page=Search&Query=elbise", true),
        Arguments.of("ty://?Page=Search&Query=%C3%BCt%C3%BC", true),
        Arguments.of("ty://?Page=Search&Queary=%C3%BCt%C3%BC", false)
    );
  }
}