package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.entity.UrlEntity;
import org.springframework.web.util.UriComponents;

public class DefaultDecoder extends Decoder {
    public DefaultDecoder(UriComponents url) {
        super(url);
    }

    @Override
    public UrlEntity decode() {
        LinkBuilder builder = new LinkBuilder();
        return builder.toUrl();
    }
}
