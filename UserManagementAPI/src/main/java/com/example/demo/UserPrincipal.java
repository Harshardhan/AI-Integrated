package com.example.demo;



import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


public class UserPrincipal implements UserDetails {

	private Long id;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	private String token; // Store the token for validation

	public UserPrincipal(Long id, String username, Collection<? extends GrantedAuthority> authorities, String token) {
		this.id = id;
		this.username = username;
		this.authorities = authorities;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null; // Not stored here
	}

	@Override
	public String getUsername() {
		return username;
	}

	
	public String getToken() {
		return token;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
