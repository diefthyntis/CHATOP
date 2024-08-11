package com.diefthyntis.chatop.diefthyntis.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtToken {
	private final String token;
	
}
