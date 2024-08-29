package com.diefthyntis.chatop.diefthyntis.controller;


import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diefthyntis.chatop.diefthyntis.dto.request.RegisterRequest;
import com.diefthyntis.chatop.diefthyntis.dto.request.SigninRequest;
import com.diefthyntis.chatop.diefthyntis.exception.EmailAddressAlreadyExistsException;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.security.JwtToken;
import com.diefthyntis.chatop.diefthyntis.service.UserService;
import com.diefthyntis.chatop.diefthyntis.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

// AuthController communique directement avec le FrontEnd, Angular ou Postman


@RestController
/*
 * @RequestMapping("/api/auth")
 */
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentificationController {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(final @RequestBody RegisterRequest registerRequest) {
		// ? signifie la généricité, donc je peux passer n'importe quel type d'objet dans la méthode responseEntity.ok
		if (userService.existsByEmailAddress(registerRequest.getEmail())) {
            throw new EmailAddressAlreadyExistsException("Email address already exists.");
        }
		
		final User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setName(registerRequest.getName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userService.save(user);
		
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));
        final String jwt = jwtUtils.generateJwtToken(authentication);
		
		
		JwtToken jwtToken= new JwtToken(jwt);
		return ResponseEntity.ok(jwtToken);
		
		
		/* dans cette application Chatop, il y a un parti pris de créer le compte utilisateur
		 * et de redigirer tout de suite sur la page du gestion du profil utilisateur
		 * mais cela pourrait etre fait en 2 temps où le user serait obligé de se connecter après validation
		 * de la création de son compte
		 */
		
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<?> connexionUser(final @RequestBody SigninRequest signinRequest) {
		
	
		
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        final String jwt = jwtUtils.generateJwtToken(authentication);
		
		
		JwtToken jwtToken= new JwtToken(jwt);
		return ResponseEntity.ok(jwtToken);
		
		
		/* dans cette application Chatop, il y a un parti pris de créer le compte utilisateur
		 * et de redigirer tout de suite sur la page du gestion du profil utilisateur
		 * mais cela pourrait etre fait en 2 temps où le user serait obligé de se connecter après validation
		 * de la création de son compte
		 */
		
	}


	// ResponseEntity<User> est une structure qui a un champ de type User
	// ResponseEntity c'est une classe générique
	@GetMapping("/me")
    public ResponseEntity<User> getMe(final Principal principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		String emailAddressUser = principal.getName();
		final User user=userService.findByEmail(emailAddressUser);
		return ResponseEntity.ok(user);
		// retourne un contenu de type MIME (PDF JSON, HTML, EXCEL tout ce qui 
		// peut être lue par un navigateur
    }
	
	
	
}

