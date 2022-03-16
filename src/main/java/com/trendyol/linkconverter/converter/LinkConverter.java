package com.trendyol.linkconverter.converter;

import java.util.function.Predicate;

public interface LinkConverter {
  String WEB_URL_DEFAULT_PAGE = "http://www.trendyol.com";
  String DEEP_LINK_DEFAULT_PAGE = "ty://?Page=Home";

  String convertToDeeplink(String webUrl);

  String convertToWebUrl(String deeplink);

  Predicate<String> isWebUrl();

  Predicate<String> isDeeplink();

  static String getDefaultDeeplink() {
    return DEEP_LINK_DEFAULT_PAGE;
  }

  static String getDefaultWebUrl() {
    return WEB_URL_DEFAULT_PAGE;
  }
}
