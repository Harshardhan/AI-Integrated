package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

	@Autowired
	private UrlRepository repo;

	@Autowired
	private Base62Encoder encoder;

	@Autowired
	private StringRedisTemplate redisTemplate;

	public String shortenUrl(String longUrl) {
		Optional<UrlMapping> existingMapping = repo.findByOriginalUrl(longUrl);
		if (existingMapping.isPresent()) {
			return existingMapping.get().getShortCode();
		}

		UrlMapping mapping = new UrlMapping();
		mapping.setOriginalUrl(longUrl);
		repo.save(mapping);

		String shortCode = encoder.encode(mapping.getId());
		mapping.setShortCode(shortCode);
		repo.save(mapping);

		return shortCode;
	}

	public String getOriginalUrl(String shortCode) {

	    System.out.println("👉 Checking Redis for key: " + shortCode);

	    String cachedUrl = redisTemplate.opsForValue().get(shortCode);

	    if (cachedUrl != null) {
	        System.out.println("🔥 Redis HIT");
	        return cachedUrl;
	    }

	    System.out.println("❌ Redis MISS → DB call");

	    String originalUrl = repo.findByShortCode(shortCode)
	            .map(UrlMapping::getOriginalUrl)
	            .orElseThrow(() -> new RuntimeException("Short code not found"));

	    System.out.println("💾 Saving to Redis: " + shortCode);

	    redisTemplate.opsForValue().set(shortCode, originalUrl);

	    return originalUrl;
	}}