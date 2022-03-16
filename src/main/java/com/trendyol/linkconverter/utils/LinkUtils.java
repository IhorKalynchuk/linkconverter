package com.trendyol.linkconverter.utils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkUtils {
  public static final String WEB_URL_CONTENT_ID_REGEX = "-p-(.*?)\\?";

  public static String getParamFromDeeplink(String deeplink, String paramName) {
    StringTokenizer tokenizer = new StringTokenizer(deeplink, "&");
    while (tokenizer.hasMoreElements()){
      String token = tokenizer.nextToken();
      if(token.startsWith(paramName))
        return token.substring(paramName.length() + 1);
    }
    return null;
  }

  public static String getContentIdFromWebUrl(String webUrl) {
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

  public static boolean isNotEmpty(String str) {
    return str != null && str.trim().length() > 0;
  }
}
