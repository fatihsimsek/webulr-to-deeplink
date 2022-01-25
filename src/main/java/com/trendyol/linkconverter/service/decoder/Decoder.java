package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.UrlEntity;
import org.springframework.web.util.UriComponents;

public abstract class Decoder {
    public abstract UrlEntity decode(UriComponents url);

    public abstract boolean isMatch(UriComponents url);
}
