package com.trendyol.linkconverter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestJsonResource {
  final static ObjectMapper mapper = new ObjectMapper();

  public static String getJson(Object obj) throws JsonProcessingException {
    return mapper.writeValueAsString(obj);
  }
}
