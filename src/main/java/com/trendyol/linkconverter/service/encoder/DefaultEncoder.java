package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultEncoder extends Encoder {

    @Override
    public DeepLink encode(UriComponents url) {
        LinkBuilder builder = new LinkBuilder();

        builder.addParam(Constant.PAGE_PARAM, Constant.HOME_PARAM_VALUE);

        return builder.toDeepLink();
    }

    @Override
    public boolean isMatch(UriComponents url) {
        return true;
    }
}
