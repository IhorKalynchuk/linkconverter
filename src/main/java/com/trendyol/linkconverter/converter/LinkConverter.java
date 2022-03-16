package com.trendyol.linkconverter.converter;

import java.util.function.Predicate;

public interface LinkConverter {
  String DEEP_LINK_HOME_PAGE = "ty://?Page=Home";

  String convertToDeeplink(String webUrl);

  Predicate<String> getWebUrlRule();

  static String getDefaultDeeplinkPage() {
    return DEEP_LINK_HOME_PAGE;
  }
}
