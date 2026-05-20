package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheConfig {
	@Autowired
	private StringRedisTemplate redisTemplate;

	public void saveToCache(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public String getFromCache(String key) {
		return redisTemplate.opsForValue().get(key);
	}
}