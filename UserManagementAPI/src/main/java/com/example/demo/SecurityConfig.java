package com.example.demo;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	private final JWTAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth

						// Public endpoints
						.requestMatchers("/api/auth/login").permitAll()

						// Register user endpoint
						.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()

						// Admin only endpoints
						.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority("ROLE_ADMIN") // Allow both ADMIN and
																									// USER to access
																									// user endpoints

						// Allow both ADMIN and USER to access user endpoints
						.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
						.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
						// Everything else requires auth
						.anyRequest().authenticated())

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
