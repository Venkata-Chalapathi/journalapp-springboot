package com.projectOne.journalApp.service;

import com.projectOne.journalApp.api.response.WeatherResponse;
import com.projectOne.journalApp.cache.AppCache;
import com.projectOne.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather(String city){
        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY, city).replace(PlaceHolders.API_KEY, apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
