package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.DeepLinkDto;
import com.trendyol.linkconverter.dto.WebUrlDto;
import com.trendyol.linkconverter.service.LinkConverterService;
import com.trendyol.linkconverter.util.TestJsonResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class LinkConverterControllerTest {

  @Mock
  LinkConverterService linkConverterService;
  @InjectMocks
  private LinkConverterController linkConverterController;


  private MockMvc sut;

  @BeforeEach
  public void setUp() {
    sut = MockMvcBuilders
        .standaloneSetup(linkConverterController)
        .build();
  }

  @Test
  void shouldConvertToDeepLink() throws Exception {
    when(linkConverterService.toDeepLink(anyString())).thenReturn("DeepLinkStub");
    sut.perform(post("/api/link/toDeepLink")
            .content(TestJsonResource.getJson(new WebUrlDto("someUrl")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().string("{\"deepLink\":\"DeepLinkStub\"}"));
  }

  @Test
  void shouldFailWhenWebUrlIsEmpty() throws Exception {
    sut.perform(post("/api/link/toDeepLink")
            .content(TestJsonResource.getJson(new WebUrlDto("")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().is(400));
  }

  @Test
  void shouldConvertWebUrl() throws Exception {
    when(linkConverterService.toWebUrl(anyString())).thenReturn("WebUrlStub");
    sut.perform(post("/api/link/toWebUrl")
            .content(TestJsonResource.getJson(new DeepLinkDto("someDeepLink")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().string("{\"webUrl\":\"WebUrlStub\"}"));
  }

  @Test
  void shouldFailWhenDeepLinkIsEmpty() throws Exception {
    sut.perform(post("/api/link/toWebUrl")
            .content(TestJsonResource.getJson(new DeepLinkDto("")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().is(400));
  }

}