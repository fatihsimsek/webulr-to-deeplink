package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import org.springframework.web.util.UriComponents;

public abstract class Encoder {
    private UriComponents url;

    public Encoder(UriComponents url) {
        this.url = url;
    }

    public abstract DeepLink encode();

    public UriComponents getUrl() {
        return url;
    }
}
