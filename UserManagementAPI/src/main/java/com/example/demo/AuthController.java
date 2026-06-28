package com.example.demo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository; // ✅ Add this

	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			UserDetails userDetails = (UserDetails) auth.getPrincipal();

			// ✅ Fetch userId from DB using username
			var user = userRepository.findByUsername(request.getUsername())
					.orElseThrow(() -> new RuntimeException("User not found"));

			// ✅ Generate token with userId + roles
			String token = jwtTokenProvider.generateToken(userDetails, user.getUserId());

			return ResponseEntity.ok(Map.of("token", token, "userId", user.getUserId(), "username", user.getUsername(),
					"roles", userDetails.getAuthorities()));

		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(401)
					.body(Map.of("error", e.getClass().getSimpleName(), "message", e.getMessage()));

		}
	}
}
