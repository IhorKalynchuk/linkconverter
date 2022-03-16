package com.trendyol.linkconverter.converter.impl;

import com.trendyol.linkconverter.converter.LinkConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Predicate;

import static com.trendyol.linkconverter.utils.LinkUtils.getContentIdFromWebUrl;
import static com.trendyol.linkconverter.utils.LinkUtils.getParamFromDeeplink;
import static com.trendyol.linkconverter.utils.LinkUtils.isNotEmpty;

@Component
public class ProductConverter implements LinkConverter {
  private static final String WEB_URL_PRODUCT_PAGE_SUBSTRING = "-p-";
  private static final String DEEP_LINK_PRODUCT_PREFIX = "ty://?Page=Product&";
  private static final String WEB_URL_PRODUCT_PREFIX = "https://www.trendyol.com/brand/name-p-";
  public static final String DEEP_LINK_CONTENT_ID = "ContentId";
  public static final String DEEP_LINK_CAMPAIGN_ID = "CampaignId";
  public static final String DEEP_LINK_MERCHANT_ID = "MerchantId";
  public static final String WEB_URL_BOUTIQUE_ID = "boutiqueId";
  public static final String WEB_URL_MERCHANT_ID = "merchantId";

  @Override
  public String convertToDeeplink(String webUrl) {
    String contentId = getContentIdFromWebUrl(webUrl);
    MultiValueMap<String, String> queryParams =
        UriComponentsBuilder.fromUriString(webUrl).build().getQueryParams();
    String boutiqueId = queryParams.getFirst(WEB_URL_BOUTIQUE_ID);
    String merchantId = queryParams.getFirst(WEB_URL_MERCHANT_ID);
    StringBuilder stringBuilder = new StringBuilder(DEEP_LINK_PRODUCT_PREFIX)
        .append(DEEP_LINK_CONTENT_ID)
        .append("=")
        .append(contentId);
    if (boutiqueId != null && !boutiqueId.isEmpty()) {
      stringBuilder.append("&")
          .append(DEEP_LINK_CAMPAIGN_ID)
          .append("=")
          .append(boutiqueId);
    }
    if (merchantId != null && !merchantId.isEmpty()) {
      stringBuilder.append("&")
          .append(DEEP_LINK_MERCHANT_ID)
          .append("=")
          .append(merchantId);
    }

    return stringBuilder.toString();
  }

  @Override
  public String convertToWebUrl(String deeplink) {
    String contentId = getParamFromDeeplink(deeplink, DEEP_LINK_CONTENT_ID);
    String boutiqueId = getParamFromDeeplink(deeplink, DEEP_LINK_CAMPAIGN_ID);
    String merchantId = getParamFromDeeplink(deeplink, DEEP_LINK_MERCHANT_ID);
    StringBuilder stringBuilder = new StringBuilder(WEB_URL_PRODUCT_PREFIX);
    stringBuilder.append(contentId);
    if (isNotEmpty(boutiqueId) || isNotEmpty(merchantId)) {
      stringBuilder.append("?");
    }
    if (isNotEmpty(boutiqueId)) {
      stringBuilder.append(WEB_URL_BOUTIQUE_ID)
          .append("=")
          .append(boutiqueId);
    }
    if (isNotEmpty(merchantId)) {
      if (isNotEmpty(boutiqueId)) {
        stringBuilder.append("&");
      }
      stringBuilder.append(WEB_URL_MERCHANT_ID)
          .append("=")
          .append(merchantId);
    }
    return stringBuilder.toString();
  }


  @Override
  public Predicate<String> isWebUrl() {
    return s -> s.contains(WEB_URL_PRODUCT_PAGE_SUBSTRING);
  }

  @Override
  public Predicate<String> isDeeplink() {
    return s -> s.startsWith(DEEP_LINK_PRODUCT_PREFIX);
  }
}
