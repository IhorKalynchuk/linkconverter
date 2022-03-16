package com.trendyol.linkconverter.converter.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
}