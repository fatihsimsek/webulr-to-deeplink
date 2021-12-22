package com.trendyol.linkconverter.entity;

import org.springframework.web.util.UriComponentsBuilder;

public class LinkBuilder {
    private UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

    public LinkBuilder addPath(String path) {
        builder.path(path);
        return this;
    }

    public LinkBuilder addParam(String name, String value) {
        builder.queryParam(name, value);
        return this;
    }

    public DeepLink toDeepLink() {
        return new DeepLink(this.buildUri("ty", ""));
    }

    public UrlEntity toUrl() {
        return new UrlEntity(buildUri("https", "www.trendyol.com"));
    }

    private String buildUri(String schema, String host) {
        return this.builder
                .scheme(schema)
                .host(host)
                .build()
                .toUriString();
    }
}
