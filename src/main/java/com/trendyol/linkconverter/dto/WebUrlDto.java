package com.trendyol.linkconverter.dto;

import com.trendyol.linkconverter.validation.annotation.WebUrl;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(example= "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
      description = "Web URL example")
  private String webUrl;
}
