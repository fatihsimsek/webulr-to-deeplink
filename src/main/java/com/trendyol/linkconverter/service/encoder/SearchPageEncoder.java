package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

@Component
@Order(1)
public class SearchPageEncoder extends Encoder {
    @Override
    public DeepLink encode(UriComponents url) {
        LinkBuilder builder = new LinkBuilder();
        List<String> queryValues = url.getQueryParams().get(Constant.QUERY_PARAM);

        builder.addParam(Constant.PAGE_PARAM, Constant.SEARCH_PARAM_VALUE)
                .addParam(Constant.QUERY_PARAM_NAME, queryValues!=null && queryValues.size()>0 ? queryValues.get(0): "");

        return builder.toDeepLink();
    }

    @Override
    public boolean isMatch(UriComponents url) {
        return  url.getPathSegments().size()==1 &&
                url.getPathSegments().get(0).equals(Constant.SEARCH_PATH_SEGMENT) &&
                url.getQueryParams().containsKey(Constant.QUERY_PARAM);
    }
}
