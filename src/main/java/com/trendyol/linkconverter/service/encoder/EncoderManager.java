package com.trendyol.linkconverter.service.encoder;

import com.trendyol.linkconverter.entity.DeepLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

@Component
public class EncoderManager {
    private List<Encoder> encoders;

    @Autowired
    public EncoderManager(List<Encoder> encoders) {
        this.encoders = encoders;
    }

    public DeepLink encode(UriComponents url) {
        for (Encoder encoder: encoders) {
            if(encoder.isMatch(url)) {
                return encoder.encode(url);
            }
        }
        return null;
    }
}
