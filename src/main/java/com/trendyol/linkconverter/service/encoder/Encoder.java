package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import org.springframework.web.util.UriComponents;

public abstract class Encoder {

    public abstract DeepLink encode(UriComponents url);

    public abstract boolean isMatch(UriComponents url);
}
