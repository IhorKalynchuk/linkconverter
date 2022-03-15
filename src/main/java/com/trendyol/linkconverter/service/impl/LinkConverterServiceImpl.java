package com.trendyol.linkconverter.service.impl;

import com.trendyol.linkconverter.service.LinkConverterService;
import org.springframework.stereotype.Component;

@Component
public class LinkConverterServiceImpl implements LinkConverterService {
  @Override
  public String toDeepLink(String webUrl) {
    return "DeepLinkStub";
  }

  @Override
  public String toWebUrl(String deepLink) {
    return "WebUrlStub";
  }
}
