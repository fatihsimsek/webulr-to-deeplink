package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.adapter.DeepLinkResponse;
import com.trendyol.linkconverter.adapter.DeepLinkToUrlRequest;
import com.trendyol.linkconverter.adapter.UrlResponse;
import com.trendyol.linkconverter.adapter.UrlToDeepLinkRequest;
import com.trendyol.linkconverter.entity.DeepLink;
import com.trendyol.linkconverter.entity.TransactionHistory;
import com.trendyol.linkconverter.entity.UrlEntity;
import com.trendyol.linkconverter.repository.TransactionHistoryRepository;
import com.trendyol.linkconverter.service.decoder.DecoderManager;
import com.trendyol.linkconverter.service.encoder.EncoderManager;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ConversionService {
    private EncoderManager encoderManager;
    private DecoderManager decoderManager;
    private TransactionHistoryRepository transactionHistoryRepository;

    public ConversionService(TransactionHistoryRepository transactionHistoryRepository,
                             EncoderManager encoderManager,
                             DecoderManager decoderManager) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.encoderManager = encoderManager;
        this.decoderManager = decoderManager;
    }

    public DeepLinkResponse urlToDeepLink(UrlToDeepLinkRequest request) {
        UriComponents url = toUrl(request);
        if(url != null) {
            DeepLink deepLink = this.encoderManager.encode(url);
            DeepLinkResponse response = toDeepLinkResponse(deepLink);

            logTransactionHistory(request.getValue(), response.getValue(), Constant.LOG_TYPE_URL_TO_DEEPLINK);
            return response;
        }
        return null;
    }

    public UrlResponse deepLinkToUrl(DeepLinkToUrlRequest request) {
        UriComponents url = toDeepLink(request);
        if(url != null) {
            UrlEntity urlEntity = this.decoderManager.decode(url);
            UrlResponse response = toUrlResponse(urlEntity);

            logTransactionHistory(request.getValue(), response.getValue(), Constant.LOG_TYPE_DEEPLINK_TO_URL);
            return response;
        }
        return null;
    }

    private UriComponents toUrl(UrlToDeepLinkRequest request) {
        if(request == null) {
            return null;
        }
        try {
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(request.getValue()).build();
            if(!uriComponents.getScheme().equals("https") || !uriComponents.getHost().equals("www.trendyol.com")) {
                return null;
            }
            return uriComponents;
        } catch (Exception ex) {
            return null;
        }
    }

    private UrlResponse toUrlResponse(UrlEntity urlEntity) {
        return new UrlResponse(urlEntity.getValue());
    }

    private UriComponents toDeepLink(DeepLinkToUrlRequest request) {
        if(request == null) {
            return null;
        }
        try {
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(request.getValue()).build();
            if(!uriComponents.getScheme().equals("ty")) {
                return null;
            }
            return uriComponents;
        } catch (Exception ex) {
            return null;
        }
    }

    private DeepLinkResponse toDeepLinkResponse(DeepLink deepLink) {
        return new DeepLinkResponse(deepLink.getValue());
    }

    private void logTransactionHistory(String request, String response, String type) {
        TransactionHistory transactionHistory = new TransactionHistory(request, response, type);
        transactionHistoryRepository.save(transactionHistory);
    }
}

