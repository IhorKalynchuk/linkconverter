package com.trendyol.linkconverter.service.impl;

import com.trendyol.linkconverter.converter.LinkConverter;
import com.trendyol.linkconverter.model.LinkConversion;
import com.trendyol.linkconverter.repository.LinkConversionRepository;
import com.trendyol.linkconverter.service.LinkConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.trendyol.linkconverter.model.Direction.TO_DEEPLINK;

@Component
public class LinkConverterServiceImpl implements LinkConverterService {

  @Autowired
  private List<LinkConverter> converterList;

  @Autowired
  private LinkConversionRepository repository;


  @Override
  public String toDeeplink(String webUrl) {
    String deeplink = null;
    for (LinkConverter converter : converterList) {
      if (converter.getWebUrlRule().test(webUrl)) {
        deeplink = converter.convertToDeeplink(webUrl);
      }
    }
    if (Objects.isNull(deeplink)) {
      deeplink = LinkConverter.getDefaultDeeplinkPage();
    }
    LinkConversion linkConversion = LinkConversion
        .builder()
        .direction(TO_DEEPLINK)
        .webUrl(webUrl)
        .deeplink(deeplink)
        .build();
    repository.save(linkConversion);

    return deeplink;
  }


  @Override
  public String toWebUrl(String deeplink) {
    String webUrl = null;
    return "WebUrlStub";
  }
}
