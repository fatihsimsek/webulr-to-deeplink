package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.LinkBuilder;
import com.trendyol.linkconverter.service.Constant;
import org.springframework.web.util.UriComponents;

import java.util.List;

public class SearchPageEncoder extends Encoder {
    public SearchPageEncoder(UriComponents url) {
        super(url);
    }

    @Override
    public DeepLink encode() {
        LinkBuilder builder = new LinkBuilder();
        List<String> queryValues = this.getUrl().getQueryParams().get(Constant.QUERY_PARAM);

        builder.addParam(Constant.PAGE_PARAM, Constant.SEARCH_PARAM_VALUE)
                .addParam(Constant.QUERY_PARAM_NAME, queryValues!=null && queryValues.size()>0 ? queryValues.get(0): "");

        return builder.toDeepLink();
    }

    public static boolean isMatch(UriComponents url) {
        return  url.getPathSegments().size()==1 &&
                url.getPathSegments().get(0).equals(Constant.SEARCH_PATH_SEGMENT) &&
                url.getQueryParams().containsKey(Constant.QUERY_PARAM);
    }
}
