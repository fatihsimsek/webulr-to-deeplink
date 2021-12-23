# Trendyol Study Case

Web service helps others to convert Trendyol.com links between mobile and web applications. Web applications use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications. When you want to redirect across applications, you should convert URLs to deeplinks or deeplinks to URLs 

## Getting Started

sh start.sh

docker-compose down

## Tech Stack
(Spring Boot / Hibernate / MySql / Swagger)

## Swagger Link
http://localhost:8080/swagger-ui.html#/conversion-controller

## API Usage

### Web URL-To-DeepLink
#### Request
`POST api/convert/url-to-deeplink`

    {
        "value":"https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"
    }
    
#### Response
    {
        "value": "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    }
    
### DeepLink-To-Web URL
#### Request
`POST api/convert/deeplink-to-url`

    {
        "value": "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    }
    
#### Response
    {
        "value": "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064"
    }  
    



