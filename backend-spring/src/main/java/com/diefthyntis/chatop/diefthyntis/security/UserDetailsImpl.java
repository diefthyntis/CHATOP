package com.diefthyntis.chatop.diefthyntis.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.diefthyntis.chatop.diefthyntis.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String emailAddress;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	private static final List<String> roles = List.of("ROLE_USER");
	// les tableaux en JAVA ont une taille fixe, contrairement aux listes

	public UserDetailsImpl(Integer id, String name, String emailAddress, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new UserDetailsImpl(user.getId(), user.getName(), user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	/*
	 * la méthode getUsername est imposée par Spring
	 * 
	 */
	@Override
	public String getUsername() {
		return emailAddress;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
