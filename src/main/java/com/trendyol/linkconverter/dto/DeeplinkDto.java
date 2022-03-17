package com.trendyol.linkconverter.dto;

import com.trendyol.linkconverter.validation.annotation.Deeplink;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(example= "ty://?Page=Product&ContentId=1925865$CampaignId=139892&MerchantId=105064",
      description = "Deeplink example")
  private String deeplink;
}
