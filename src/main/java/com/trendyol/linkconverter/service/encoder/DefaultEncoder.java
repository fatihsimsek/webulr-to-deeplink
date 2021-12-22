package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.web.util.UriComponents;

public class DefaultEncoder extends Encoder {
    public DefaultEncoder(UriComponents url) {
        super(url);
    }

    @Override
    public DeepLink encode() {
        LinkBuilder builder = new LinkBuilder();

        builder.addParam(Constant.PAGE_PARAM, Constant.HOME_PARAM_VALUE);

        return builder.toDeepLink();
    }
}
