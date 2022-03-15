package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.DeepLinkDto;
import com.trendyol.linkconverter.dto.WebUrlDto;
import com.trendyol.linkconverter.service.LinkConverterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "LinkConverter")
@RequestMapping("/api/link")
@AllArgsConstructor
@Slf4j
public class LinkConverterController {
  private static final Logger LOGGER = LoggerFactory.getLogger(LinkConverterController.class);
  private final LinkConverterService linkConverterService;

  @PostMapping(path = "/toDeepLink", produces = {MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Convert Web URL do DeepLink", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(description = " success", responseCode = "200",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeepLinkDto.class))),
      @ApiResponse(description = "Bad request", responseCode = "400", content = @Content)
  })
  @ResponseBody
  public DeepLinkDto toDeepLink(@RequestBody @Valid WebUrlDto webUrl) {
    String deepLink = linkConverterService.toDeepLink(webUrl.getWebUrl());
    LOGGER.info("Successfully converted webUrl '{}' to deepLink '{}'", webUrl, deepLink);
    return new DeepLinkDto(deepLink);
  }

  @PostMapping(path = "/toWebUrl", produces = {MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Convert DeepLink do Web URL", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(description = " success", responseCode = "200",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = WebUrlDto.class))),
      @ApiResponse(description = "Bad request", responseCode = "400", content = @Content)
  })
  @ResponseBody
  public WebUrlDto toWebUrl(@RequestBody @Valid DeepLinkDto deepLink) {
    String webUrl = linkConverterService.toWebUrl(deepLink.getDeepLink());
    LOGGER.info("Successfully converted deepLink '{}' to webUrl '{}'", deepLink, webUrl);
    return new WebUrlDto(webUrl);
  }

}
