package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "urlshortener-service")
public interface URLShortenerClient {

    @PostMapping(value = "/api/url/shorten", consumes = "application/json")
    String shortenURL(@RequestBody UrlRequest request);
}