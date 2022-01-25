package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.entity.UrlEntity;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

@Component
@Order(2)
public class SearchPageDecoder extends Decoder {

    @Override
    public UrlEntity decode(UriComponents url) {
        LinkBuilder builder = new LinkBuilder();
        builder.addPath(Constant.SEARCH_PATH_SEGMENT);

        url.getQueryParams().forEach((k,v) -> {
            if (k.equals(Constant.QUERY_PARAM_NAME)) {
                builder.addParam(Constant.QUERY_PARAM, v != null && v.size() > 0 ? v.get(0) : "");
            }
        });

        return builder.toUrl();
    }

    @Override
    public boolean isMatch(UriComponents url) {
        if(url.getQueryParams().containsKey(Constant.PAGE_PARAM) && url.getQueryParams().containsKey(Constant.QUERY_PARAM_NAME)) {
            List<String> paramValues = url.getQueryParams().get(Constant.PAGE_PARAM);
            return paramValues != null && paramValues.size() > 0 ? paramValues.get(0).equals(Constant.SEARCH_PARAM_VALUE) : false;
        }
        return false;
    }
}
