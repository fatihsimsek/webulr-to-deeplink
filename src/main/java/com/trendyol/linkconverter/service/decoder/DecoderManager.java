package com.trendyol.linkconverter.service.decoder;

import com.trendyol.linkconverter.entity.UrlEntity;
import com.trendyol.linkconverter.service.encoder.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

@Component
public class DecoderManager {

    private List<Decoder> decoders;

    @Autowired
    public DecoderManager(List<Decoder> decoders) {
        this.decoders = decoders;
    }

    public UrlEntity decode(UriComponents url) {
        for (Decoder decoder: decoders) {
            if(decoder.isMatch(url)) {
                return decoder.decode(url);
            }
        }
        return null;
    }
}
