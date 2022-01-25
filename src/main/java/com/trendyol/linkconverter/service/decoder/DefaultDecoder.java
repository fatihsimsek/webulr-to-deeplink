package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.entity.UrlEntity;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultDecoder extends Decoder {

    @Override
    public UrlEntity decode(UriComponents url) {
        LinkBuilder builder = new LinkBuilder();
        return builder.toUrl();
    }

    @Override
    public boolean isMatch(UriComponents url) {
        return true;
    }
}
