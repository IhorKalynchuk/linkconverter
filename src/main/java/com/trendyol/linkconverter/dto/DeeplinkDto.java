package com.trendyol.linkconverter.dto;

import com.trendyol.linkconverter.validation.annotation.Deeplink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeeplinkDto {
  @NotEmpty(message = "Deep Link should not be empty")
  @Deeplink
  private String deeplink;
}
