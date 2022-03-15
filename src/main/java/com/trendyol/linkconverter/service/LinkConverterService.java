package com.trendyol.linkconverter.service;

public interface LinkConverterService {
  String toDeepLink(String webUrl);
  String toWebUrl(String deepLink);
}
