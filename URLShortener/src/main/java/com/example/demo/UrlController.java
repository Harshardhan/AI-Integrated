package com.example.demo;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
public class UrlController {

	@Autowired
	private UrlService service;

	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

	    String originalUrl = service.getOriginalUrl(shortCode);

	    return ResponseEntity.status(302)
	            .header("Location", originalUrl)
	            .build();
	}
	

	@PostMapping("/shorten")
	public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody UrlRequest request) {

	    String shortCode = service.shortenUrl(request.getLongUrl());

	    return ResponseEntity.ok(Map.of(
	            "shortUrl", "http://localhost:8079/" + shortCode
	    ));
	}

	
}