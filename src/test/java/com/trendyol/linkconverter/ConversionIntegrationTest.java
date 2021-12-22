package com.trendyol.linkconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.linkconverter.adapter.DeepLinkResponse;
import com.trendyol.linkconverter.adapter.DeepLinkToUrlRequest;
import com.trendyol.linkconverter.adapter.UrlResponse;
import com.trendyol.linkconverter.adapter.UrlToDeepLinkRequest;
import com.trendyol.linkconverter.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConversionIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConversionService conversionService;

    @Test
    void convertProductPageUrlToDeepLink_OK() throws Exception {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064");

        DeepLinkResponse response = new DeepLinkResponse("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        given(conversionService.urlToDeepLink(any(UrlToDeepLinkRequest.class))).willReturn(response);

        this.mockMvc.perform(post("/api/convert/url-to-deeplink")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());
    }

    @Test
    void convertProductPageUrlToDeepLink_NotOK() throws Exception {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064");

        given(conversionService.urlToDeepLink(any(UrlToDeepLinkRequest.class))).willReturn(null);

        this.mockMvc.perform(post("/api/convert/url-to-deeplink")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().is4xxClientError());
    }

    @Test
    void convertProductPageDeepLinkToUrl_OK() throws Exception {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        UrlResponse response = new UrlResponse("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");

        given(conversionService.deepLinkToUrl(any(DeepLinkToUrlRequest.class))).willReturn(response);

        this.mockMvc.perform(post("/api/convert/deeplink-to-url")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());
    }

    @Test
    void convertProductPageDeepLinkToUrl_NotOK() throws Exception {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("//?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        given(conversionService.deepLinkToUrl(any(DeepLinkToUrlRequest.class))).willReturn(null);

        this.mockMvc.perform(post("/api/convert/deeplink-to-url")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().is4xxClientError());
    }
}
