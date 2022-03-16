package com.trendyol.linkconverter.converter.impl;

import com.trendyol.linkconverter.converter.LinkConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Predicate;

import static com.trendyol.linkconverter.utils.LinkUtils.getParamFromDeeplink;

@Component
public class  SearchConverter implements LinkConverter {
  private static final String WEB_URL_SEARCH_PAGE_PREFIX = "https://www.trendyol.com/sr?q=";
  private static final String DEEP_LINK_SEARCH_PAGE_PREFIX = "ty://?Page=Search&Query=";


  @Override
  public String convertToDeeplink(String webUrl) {
    MultiValueMap<String, String> queryParams =
        UriComponentsBuilder.fromUriString(webUrl).build().getQueryParams();
    String queryParam = queryParams.getFirst("q");

    return DEEP_LINK_SEARCH_PAGE_PREFIX + queryParam;
  }

  @Override
  public String convertToWebUrl(String deeplink) {
    String searchParam = getParamFromDeeplink(deeplink, "Query");
    return WEB_URL_SEARCH_PAGE_PREFIX + searchParam;
  }

  @Override
  public Predicate<String> isWebUrl() {
    return s -> s.startsWith(WEB_URL_SEARCH_PAGE_PREFIX);
  }

  @Override
  public Predicate<String> isDeeplink() {
    return s -> s.startsWith(DEEP_LINK_SEARCH_PAGE_PREFIX);
  }
}
