package com.trendyol.linkconverter.dto;

import com.trendyol.linkconverter.validation.annotation.WebUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebUrlDto {
  @NotEmpty(message = "Web URL should not be empty")
  @WebUrl
  private String webUrl;
}
