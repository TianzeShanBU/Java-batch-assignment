package com.example.search.controller;

import com.example.search.filter.SearchFilter;
import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;
    public static final String CORRELATION_ID = "correlation-id";

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails(@RequestParam String cities) {
        HttpHeaders hd = new HttpHeaders();
        hd.set(CORRELATION_ID,SearchFilter.uuid);
        return new ResponseEntity<>(searchService.getWeatherByCityName(cities),hd, HttpStatus.OK);
    }
}
