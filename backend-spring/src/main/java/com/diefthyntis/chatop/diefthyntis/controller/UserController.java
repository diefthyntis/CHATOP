package com.diefthyntis.chatop.diefthyntis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diefthyntis.chatop.diefthyntis.dto.response.UserResponse;
import com.diefthyntis.chatop.diefthyntis.mapping.UserMapping;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	private final UserMapping userMapping;

	@GetMapping("/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse getUserById(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		return userMapping.mapUserToUserResponse(user);
	}

}
