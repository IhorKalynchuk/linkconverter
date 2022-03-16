package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.DeeplinkDto;
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
  void shouldConvertToDeeplink() throws Exception {
    when(linkConverterService.toDeeplink(anyString())).thenReturn("DeeplinkStub");
    sut.perform(post("/api/link/toDeeplink")
            .content(TestJsonResource.getJson(new WebUrlDto("https://www.trendyol.com/Hesabim/#/Siparisierim")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().string("{\"deeplink\":\"DeeplinkStub\"}"));
  }

  @Test
  void shouldFailWhenWebUrlIsEmpty() throws Exception {
    sut.perform(post("/api/link/toDeeplink")
            .content(TestJsonResource.getJson(new WebUrlDto("")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().is(400));
  }

  @Test
  void shouldConvertWebUrl() throws Exception {
    when(linkConverterService.toWebUrl(anyString())).thenReturn("WebUrlStub");
    sut.perform(post("/api/link/toWebUrl")
            .content(TestJsonResource.getJson(new DeeplinkDto("ty://?Page=Home")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().string("{\"webUrl\":\"WebUrlStub\"}"));
  }

  @Test
  void shouldFailWhenDeeplinkIsEmpty() throws Exception {
    sut.perform(post("/api/link/toWebUrl")
            .content(TestJsonResource.getJson(new DeeplinkDto("")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(status().is(400));
  }

}