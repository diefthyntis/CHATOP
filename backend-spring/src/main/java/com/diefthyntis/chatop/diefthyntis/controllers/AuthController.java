package com.diefthyntis.chatop.diefthyntis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diefthyntis.chatop.diefthyntis.dto.JwtToken;
import com.diefthyntis.chatop.diefthyntis.dto.request.RegisterRequest;
import com.diefthyntis.chatop.diefthyntis.entities.User;
import com.diefthyntis.chatop.diefthyntis.services.UserService;

import lombok.RequiredArgsConstructor;

// AuthController communique directement avec le FrontEnd, Angular ou Postman

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	private final UserService userService;
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(final @RequestBody RegisterRequest registerRequest) {
		// ? signifie la généricité, donc je peux passer n'importe quel type d'objet dans la méthode responseEntity.ok
		final User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setName(registerRequest.getName());
		user.setPassword(registerRequest.getPassword());
		userService.save(user);
		JwtToken jwtToken= new JwtToken("test");
		return ResponseEntity.ok(jwtToken);
	}
}
