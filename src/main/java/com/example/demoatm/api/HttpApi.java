package com.example.demoatm.api;

import com.example.demoatm.api.pojo.CurrencyItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

@Component
@Data
public class HttpApi {

    /**
     * This method read api which --> https://cbu.uz/oz/arkhiv-kursov-valyut/json/
     * @return List<>CurrencyItem</>
     */
    public static List<CurrencyItem> currencyItemList()  {
        HttpGet get = new HttpGet("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
        HttpClient client = HttpClients.createDefault();
        HttpResponse response;
        Reader reader;
        try {
            response = client.execute(get);
            reader = new InputStreamReader(response.getEntity().getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        List<CurrencyItem> currencyItemList = gson.fromJson(reader, new TypeToken<List<CurrencyItem>>() {
        }.getType());
        return currencyItemList;
    }

    /**
     * Method gets data api only  about USD
     * @return Double
     */
    @Bean
    public static Double getOneUsdCurrencyRate()  {
        String rate = "";
        for (CurrencyItem currencyItem1 : currencyItemList()) {
            if (currencyItem1.getId().equals("69")) {
                currencyItem1.getCcyNm_EN();
                rate = currencyItem1.getRate();
            }
        }
        Double uzRateOfUsd = Double.parseDouble(rate);
        return uzRateOfUsd;
    }
}
