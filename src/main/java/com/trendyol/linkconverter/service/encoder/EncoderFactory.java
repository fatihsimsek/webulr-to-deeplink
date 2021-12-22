package com.trendyol.linkconverter.service.encoder;

import org.springframework.web.util.UriComponents;

public class EncoderFactory {
    private EncoderFactory() {
    }

    public static Encoder getEncoder(UriComponents url) {
        if(ProductPageEncoder.isMatch(url)) {
            return new ProductPageEncoder(url);
        } else if(SearchPageEncoder.isMatch(url)) {
            return new SearchPageEncoder(url);
        }
        return new DefaultEncoder(url);
    }
}
