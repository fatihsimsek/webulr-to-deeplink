package com.trendyol.linkconverter;

import com.trendyol.linkconverter.adapter.DeepLinkResponse;
import com.trendyol.linkconverter.adapter.DeepLinkToUrlRequest;
import com.trendyol.linkconverter.adapter.UrlResponse;
import com.trendyol.linkconverter.adapter.UrlToDeepLinkRequest;
import com.trendyol.linkconverter.repository.TransactionHistoryRepository;
import com.trendyol.linkconverter.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
public class DecoderUnitTest {
    @MockBean
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private ConversionService conversionService;

    @Test
    void merchantIdAndCampaignIdExistsProductPageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064"), "ProductPageDeepLinkToUrl-Both MerchantId and CampaignId Exists");
    }

    @Test
    void merchantIdAndBoutiqueIdNotExistsProductPageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Product&ContentId=1925865");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com/brand/name-p-1925865"), "ProductPageDeepLinkToUrl-Both MerchantId and BoutiqueId Not Exists");
    }

    @Test
    void merchantIdExistsProductPageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Product&ContentId=1925865&MerchantId=105064");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064"), "ProductPageDeepLinkToUrl-MerchantId Exists");
    }


    @Test
    void campaignIdExistsProductPageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Product&ContentId=1925865&CampaignId=439892");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892"), "ProductPageDeepLinkToUrl-CampaignId Exists");
    }

    @Test
    void queryExistsSearchPageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Search&Query=elbise");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com/sr?q=elbise"), "SearchPageDeepLinkToUrl-QueryExists");
    }

    @Test
    void queryValueEncodedSearchPageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Search&Query=%C3%BCt%C3%BC");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC"), "SearchPageDeepLinkToUrl-Query Value Encoded");
    }

    @Test
    void scenarioFirstHomePageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Favorites");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com"), "HomePageDeepLinkToUrl-ScenarioFirst");
    }

    @Test
    void scenarioSecondHomePageDeepLinkToUrl() {
        DeepLinkToUrlRequest request = new DeepLinkToUrlRequest();
        request.setValue("ty://?Page=Orders");

        UrlResponse response = conversionService.deepLinkToUrl(request);
        Assert.isTrue(response.getValue().equals("https://www.trendyol.com"), "HomePageDeepLinkToUrl-ScenarioSecond");
    }
}
