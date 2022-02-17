package com.example.search.service;

import com.example.common.exception.RestTemplateException;
import com.example.search.filter.SearchFilter;
import com.example.search.pojo.DataWraper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public Map<String,Map> getWeatherByCityName(String cities) {
        Map<String,Map> res = new ConcurrentHashMap<>();
        String[] citiesArr = cities.split(",");
        List<CompletableFuture> futures = new ArrayList();
        for(String s : citiesArr){
            futures.add( CompletableFuture.supplyAsync(() -> {
//                String url = "http://weather-details-service/details?city=" + s;
//                DataWraper ls = restTemplate.getForObject(url, DataWraper.class);
//                if(ls == null){
//                    throw new RestTemplateException("FAIL_TO_GET_ID_OF_CITY in SearchService.java");
//                }
//                Integer id = ls.getData().get(0);
//                String url1 = "http://weather-details-service/details/" + id;
//                Map<String, Map> m = restTemplate.getForObject(url1, HashMap.class);
//                if(m == null){
//                    throw new RestTemplateException("FAIL_TO_GET_WEATHER_DETAILS_BY_ID in SearchService.java");
//                }
//                res.put(s,m);
//                logger.info(Thread.currentThread().getName());
//                return m;

                // cannot use "http://weather-details-service/details?city=" + s !!!!!NOT SURE WHY
                String url = "http://localhost:8000/details?city=" + s;
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("correlation-id", SearchFilter.uuid);
                HttpEntity request = new HttpEntity(headers);
                ResponseEntity<DataWraper> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        request,
                        DataWraper.class
                );
                DataWraper ls = response.getBody();
                if(ls == null){
                    throw new RestTemplateException("FAIL_TO_GET_ID_OF_CITY in SearchService.java");
                }
                Integer id = ls.getData().get(0);
                String url1 = "http://localhost:8000/details/" + id;
                Map<String, Map> m = restTemplate.getForObject(url1, HashMap.class);
                if(m == null){
                    throw new RestTemplateException("FAIL_TO_GET_WEATHER_DETAILS_BY_ID in SearchService.java");
                }
                res.put(s,m);
                logger.info(Thread.currentThread().getName());
                return m;
            }));

        }

//        try{
//            Thread.sleep(3000);
//        } catch(Exception e){
//
//        }

//      stay in blocked status before loop finish
        CompletableFuture.allOf(futures.toArray(new  CompletableFuture[futures.size()])).join();
        return res;
    }
}
