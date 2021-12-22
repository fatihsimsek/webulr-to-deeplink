package com.trendyol.linkconverter;

import com.trendyol.linkconverter.adapter.DeepLinkResponse;
import com.trendyol.linkconverter.adapter.UrlToDeepLinkRequest;
import com.trendyol.linkconverter.repository.TransactionHistoryRepository;
import com.trendyol.linkconverter.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
public class EncoderUnitTest {
    @MockBean
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private ConversionService conversionService;

    @Test
    void merchantIdAndBoutiqueIdExistsProductPageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"), "ProductPageUrlToDeepLink-Both MerchantId and BoutiqueId Exists");
    }

    @Test
    void merchantIdAndBoutiqueIdNotExistsProductPageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Product&ContentId=1925865"), "ProductPageUrlToDeepLink-Both MerchantId and BoutiqueId Not Exists");
    }

    @Test
    void merchantIdExistsProductPageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Product&ContentId=1925865&MerchantId=105064"), "ProductPageUrlToDeepLink-MerchantId Exists");
    }


    @Test
    void boutiqueIdExistsProductPageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Product&ContentId=1925865&CampaignId=439892"), "ProductPageUrlToDeepLink-BoutiqueId Exists");
    }

    @Test
    void queryExistsSearchPageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/sr?q=elbise");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Search&Query=elbise"), "SearchPageUrlToDeepLink-QueryExists");
    }

    @Test
    void queryValueEncodedSearchPageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Search&Query=%C3%BCt%C3%BC"), "SearchPageUrlToDeepLink-Query Value Encoded");
    }

    @Test
    void scenarioFirstHomePageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/Hesabim/Favoriler");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Home"), "HomePageUrlToDeepLink-ScenarioFirst");
    }

    @Test
    void scenarioSecondHomePageUrlToDeepLink() {
        UrlToDeepLinkRequest request = new UrlToDeepLinkRequest();
        request.setValue("https://www.trendyol.com/Hesabim/#/Siparislerim");

        DeepLinkResponse response = conversionService.urlToDeepLink(request);
        Assert.isTrue(response.getValue().equals("ty://?Page=Home"), "HomePageUrlToDeepLink-ScenarioSecond");
    }
}
