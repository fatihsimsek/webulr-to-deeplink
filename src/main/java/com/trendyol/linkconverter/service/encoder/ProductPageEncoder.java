package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.regex.Pattern;

@Component
@Order(1)
public class ProductPageEncoder extends Encoder {
    private static Pattern productPagePattern = Pattern.compile("^(.*)-p-(\\d+)$");

    @Override
    public DeepLink encode(UriComponents url) {
        LinkBuilder builder = new LinkBuilder();
        String contentId = getContentId(url);

        builder.addParam(Constant.PAGE_PARAM, Constant.PRODUCT_PARAM_VALUE)
                .addParam(Constant.CONTENT_ID_PARAM, contentId);

        url.getQueryParams().forEach((k,v) -> {
            if (k.equals(Constant.BOUTIQUE_ID_PARAM)) {
                builder.addParam(Constant.CAMPAIGN_ID_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
            else if (k.equals(Constant.MERCHANT_ID_PARAM)) {
                builder.addParam(Constant.MERCHANT_ID_UPPER_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
        });

        return builder.toDeepLink();
    }

    @Override
    public boolean isMatch(UriComponents url) {
        return url.getPathSegments().size() == 2 &&
                productPagePattern.matcher(url.getPathSegments().get(1)).matches();
    }

    private String getContentId(UriComponents url) {
        int index = url.getPathSegments().get(1).indexOf("-p-");
        return url.getPathSegments().get(1).substring(index+3);
    }
}
