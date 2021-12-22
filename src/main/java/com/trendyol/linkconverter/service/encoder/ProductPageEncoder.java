package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.web.util.UriComponents;

import java.util.regex.Pattern;

public class ProductPageEncoder extends Encoder {
    private static Pattern productPagePattern = Pattern.compile("^(.*)-p-(\\d+)$");

    public ProductPageEncoder(UriComponents url) {
        super(url);
    }

    @Override
    public DeepLink encode() {
        LinkBuilder builder = new LinkBuilder();
        String contentId = getContentId();

        builder.addParam(Constant.PAGE_PARAM, Constant.PRODUCT_PARAM_VALUE)
                .addParam(Constant.CONTENT_ID_PARAM, contentId);

        this.getUrl().getQueryParams().forEach((k,v) -> {
            if (k.equals(Constant.BOUTIQUE_ID_PARAM)) {
                builder.addParam(Constant.CAMPAIGN_ID_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
            else if (k.equals(Constant.MERCHANT_ID_PARAM)) {
                builder.addParam(Constant.MERCHANT_ID_UPPER_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
        });

        return builder.toDeepLink();
    }

    public static boolean isMatch(UriComponents url) {
        return url.getPathSegments().size() == 2 &&
                productPagePattern.matcher(url.getPathSegments().get(1)).matches();
    }

    private String getContentId() {
        int index = this.getUrl().getPathSegments().get(1).indexOf("-p-");
        return this.getUrl().getPathSegments().get(1).substring(index+3);
    }
}
