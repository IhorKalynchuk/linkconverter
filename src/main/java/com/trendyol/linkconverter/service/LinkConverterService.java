package com.trendyol.linkconverter.service;

public interface LinkConverterService {
  String toDeeplink(String webUrl);
  String toWebUrl(String deeplink);
}
