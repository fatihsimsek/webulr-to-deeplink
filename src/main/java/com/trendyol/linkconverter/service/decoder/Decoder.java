package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.UrlEntity;
import org.springframework.web.util.UriComponents;

public abstract class Decoder {
    private UriComponents url;

    public Decoder(UriComponents url) {
        this.url = url;
    }

    public abstract UrlEntity decode();

    public UriComponents getUrl() {
        return url;
    }
}
