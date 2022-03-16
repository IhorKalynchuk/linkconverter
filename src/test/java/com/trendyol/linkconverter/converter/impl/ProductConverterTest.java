package com.trendyol.linkconverter.converter.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProductConverterTest {

  ProductConverter productConverter = new ProductConverter();

  @ParameterizedTest
  @MethodSource
  void should_convertToDeeplink(String webUrl, String deeplink) {

    String result = productConverter.convertToDeeplink(webUrl);
    assertThat(result).isEqualTo(deeplink);
  }

  static Stream<Arguments> should_convertToDeeplink() {
    return Stream.of(
        Arguments.of("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064", "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"),
        Arguments.of("https://www.trendyol.com/casio/erkek-koi-ssti-p-1925865", "ty://?Page=Product&ContentId=1925865"),
        Arguments.of("https://www.trendyol.com/casio/erkek-koi-ssti-p-1925865?boutiqueId=439892", "ty://?Page=Product&ContentId=1925865&CampaignId=439892"),
        Arguments.of("https://www.trendyol.com/casio/erkek-koi-ssti-p-1925865?merchantId=105064", "ty://?Page=Product&ContentId=1925865&MerchantId=105064")
    );
  }

  @ParameterizedTest
  @MethodSource
  void should_convertToWebUrl(String deeplink, String webUrl) {

    String result = productConverter.convertToWebUrl(deeplink);
    assertThat(result).isEqualTo(webUrl);
  }

  static Stream<Arguments> should_convertToWebUrl() {
    return Stream.of(
        Arguments.of("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064"),
        Arguments.of("ty://?Page=Product&ContentId=1925865", "https://www.trendyol.com/brand/name-p-1925865"),
        Arguments.of("ty://?Page=Product&ContentId=1925865&CampaignId=439892", "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892"),
        Arguments.of("ty://?Page=Product&ContentId=1925865&MerchantId=105064", "https://www.trendyol.com/brand/name-p-1925865?merchantId=105064")
    );
  }

  @ParameterizedTest
  @MethodSource
  void should_checkIfWebSearch(String webUrl, boolean expected) {

    Predicate<String> predicate = productConverter.isWebUrl();
    boolean result = predicate.test(webUrl);
    assertThat(result).isEqualTo(expected);
  }

  static Stream<Arguments> should_checkIfWebSearch() {
    return Stream.of(
        Arguments.of("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064", true),
        Arguments.of("https://www.trendyol.com/brand/name-p-1925865", true),
        Arguments.of("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892", true),
        Arguments.of("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064", true),
        Arguments.of("https://www.trendyol.com/brand/name-pp-1925865?merchantId=105064", false)
    );
  }
  @ParameterizedTest
  @MethodSource

  void should_checkIfDeeplinkSearch(String deeplink, boolean expected) {

    Predicate<String> predicate = productConverter.isDeeplink();
    boolean result = predicate.test(deeplink);
    assertThat(expected).isEqualTo(result);
  }

  static Stream<Arguments> should_checkIfDeeplinkSearch() {
    return Stream.of(
        Arguments.of("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", true),
        Arguments.of("ty://?Page=Product&ContentId=1925865", true),
        Arguments.of("ty://?Page=Product&ContentId=1925865&CampaignId=439892", true),
        Arguments.of("ty://?Page=Product&ContentId=1925865&MerchantId=105064", true),
        Arguments.of("ty://?Page=Search&ContentId=1925865&MerchantId=105064", false)
    );
  }
}