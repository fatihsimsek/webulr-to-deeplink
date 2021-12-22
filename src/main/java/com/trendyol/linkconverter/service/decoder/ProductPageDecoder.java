package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.entity.UrlEntity;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.web.util.UriComponents;

import java.util.List;

public class ProductPageDecoder extends Decoder {
    public ProductPageDecoder(UriComponents url) {
        super(url);
    }

    @Override
    public UrlEntity decode() {
        LinkBuilder builder = new LinkBuilder();

        this.getUrl().getQueryParams().forEach((k,v) -> {
            if (k.equals(Constant.CONTENT_ID_PARAM)) {
                builder.addPath("/brand").addPath("/name-p-" + (v != null && v.size() > 0 ? v.get(0) : ""));
            }
            else if (k.equals(Constant.CAMPAIGN_ID_PARAM)) {
                builder.addParam(Constant.BOUTIQUE_ID_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
            else if (k.equals(Constant.MERCHANT_ID_UPPER_PARAM)) {
                builder.addParam(Constant.MERCHANT_ID_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
        });

        return builder.toUrl();
    }

    public static boolean isMatch(UriComponents url) {
        if(url.getQueryParams().containsKey(Constant.PAGE_PARAM) && url.getQueryParams().containsKey(Constant.CONTENT_ID_PARAM)) {
            List<String> paramValues = url.getQueryParams().get(Constant.PAGE_PARAM);
            return paramValues != null && paramValues.size() > 0 ? paramValues.get(0).equals(Constant.PRODUCT_PARAM_VALUE) : false;
        }
        return false;
    }
}
