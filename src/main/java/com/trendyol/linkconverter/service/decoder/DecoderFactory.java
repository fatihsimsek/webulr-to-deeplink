package com.trendyol.linkconverter.service.decoder;

import org.springframework.web.util.UriComponents;

public class DecoderFactory {

    private DecoderFactory() {
    }

    public static Decoder getDecoder(UriComponents url) {
        if(ProductPageDecoder.isMatch(url)) {
            return new ProductPageDecoder(url);
        } else if(SearchPageDecoder.isMatch(url)) {
            return new SearchPageDecoder(url);
        }
        return new DefaultDecoder(url);
    }
}
