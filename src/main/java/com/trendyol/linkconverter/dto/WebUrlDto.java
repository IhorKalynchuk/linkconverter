  package com.trendyol.linkconverter.dto;

  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  import javax.validation.constraints.NotEmpty;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public class WebUrlDto {
    @NotEmpty(message = "Web URL should not be empty")
    private String webUrl;
  }
