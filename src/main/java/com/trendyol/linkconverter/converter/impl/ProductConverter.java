package com.trendyol.linkconverter.converter.impl;

import com.trendyol.linkconverter.converter.LinkConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductConverter implements LinkConverter {
  private static final String PRODUCT_PAGE_SUBSTRING = "-p-";
  private static final String DEEP_LINK_PRODUCT_PREFIX = "ty://?Page=Product";
  public static final String DEEP_LINK_CONTENT_ID = "&ContentId=";
  public static final String DEEP_LINK_CAMPAIGN_ID = "CampaignId=";
  public static final String DEEP_LINK_MERCHANT_ID = "MerchantId=";
  public static final String WEB_URL_BOUTIQUE_ID = "boutiqueId";
  public static final String WEB_URL_MERCHANT_ID = "merchantId";
  public static final String WEB_URL_CONTENT_ID_REGEX = "-p-(.*?)\\?";

  @Override
  public String convertToDeeplink(String webUrl) {
    String contentId = getContentId(webUrl);
    MultiValueMap<String, String> queryParams =
        UriComponentsBuilder.fromUriString(webUrl).build().getQueryParams();
    String boutiqueId = queryParams.getFirst(WEB_URL_BOUTIQUE_ID);
    String merchantId = queryParams.getFirst(WEB_URL_MERCHANT_ID);
    StringBuilder stringBuilder = new StringBuilder(DEEP_LINK_PRODUCT_PREFIX)
        .append(DEEP_LINK_CONTENT_ID)
        .append(contentId);
    if (boutiqueId != null && !boutiqueId.isEmpty()) {
      stringBuilder.append("&").append(DEEP_LINK_CAMPAIGN_ID).append(boutiqueId);
    }
    if (merchantId != null && !merchantId.isEmpty()) {
      stringBuilder.append("&").append(DEEP_LINK_MERCHANT_ID).append(merchantId);
    }

    return stringBuilder.toString();
  }

  private String getContentId(String webUrl) {
    if (webUrl.contains("?")) {
      Pattern pattern = Pattern.compile(WEB_URL_CONTENT_ID_REGEX);
      Matcher matcher = pattern.matcher(webUrl);
      if (matcher.find()) {
        return matcher.group(1);
      }
    } else {
      return webUrl.substring(webUrl.indexOf("-p-") + 3);
    }
    throw new UnsupportedOperationException("Cannot extract contentId");
  }

  @Override
  public Predicate<String> getWebUrlRule() {
    return s -> s.contains(PRODUCT_PAGE_SUBSTRING);
  }
}
