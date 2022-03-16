package com.trendyol.linkconverter.service.impl;

import com.trendyol.linkconverter.converter.LinkConverter;
import com.trendyol.linkconverter.model.LinkConversion;
import com.trendyol.linkconverter.repository.LinkConversionRepository;
import com.trendyol.linkconverter.service.LinkConverterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.trendyol.linkconverter.model.Direction.TO_DEEPLINK;
import static com.trendyol.linkconverter.model.Direction.TO_WEB_URL;

@Component
@AllArgsConstructor
public class LinkConverterServiceImpl implements LinkConverterService {

  @Autowired
  private List<LinkConverter> converterList;

  @Autowired
  private LinkConversionRepository repository;


  @Override
  public String convertToDeeplink(String webUrl) {
    String deeplink = null;
    for (LinkConverter converter : converterList) {
      if (converter.isWebUrl().test(webUrl)) {
        deeplink = converter.convertToDeeplink(webUrl);
      }
    }
    if (Objects.isNull(deeplink)) {
      deeplink = LinkConverter.getDefaultDeeplink();
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
  public String convertToWebUrl(String deeplink) {
    String webUrl = null;
    for (LinkConverter converter : converterList) {
      if (converter.isDeeplink().test(deeplink)) {
        webUrl = converter.convertToWebUrl(deeplink);
      }
    }
    if (Objects.isNull(webUrl)) {
      webUrl = LinkConverter.getDefaultWebUrl();
    }
    LinkConversion linkConversion = LinkConversion
        .builder()
        .direction(TO_WEB_URL)
        .webUrl(webUrl)
        .deeplink(deeplink)
        .build();
    repository.save(linkConversion);
    return webUrl;
  }
}
