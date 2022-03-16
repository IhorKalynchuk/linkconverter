package com.trendyol.linkconverter.service;

public interface LinkConverterService {
  String convertToDeeplink(String webUrl);
  String convertToWebUrl(String deeplink);
}
