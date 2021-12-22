package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.adapter.DeepLinkResponse;
import com.trendyol.linkconverter.adapter.DeepLinkToUrlRequest;
import com.trendyol.linkconverter.adapter.UrlResponse;
import com.trendyol.linkconverter.adapter.UrlToDeepLinkRequest;
import com.trendyol.linkconverter.service.ConversionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class ConversionController {

    private ConversionService conversionService;

    @Autowired
    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/url-to-deeplink")
    @ApiOperation(value = "UrlToDeeplink")
    @Transactional
    public ResponseEntity urlToDeeplink(@RequestBody UrlToDeepLinkRequest request) {
        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        if(response == null){
            return ResponseEntity.badRequest().body("Request is not valid");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deeplink-to-url")
    @ApiOperation(value = "DeeplinkToUrl")
    @Transactional
    public ResponseEntity deeplinkToUrl(@RequestBody DeepLinkToUrlRequest request) {
        UrlResponse response = conversionService.deepLinkToUrl(request);
        if(response == null){
            return ResponseEntity.badRequest().body("Request is not valid");
        }
        return ResponseEntity.ok(response);
    }
}
