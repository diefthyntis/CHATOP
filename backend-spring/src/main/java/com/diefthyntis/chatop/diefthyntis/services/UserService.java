package com.diefthyntis.chatop.diefthyntis.services;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.entities.User;
import com.diefthyntis.chatop.diefthyntis.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	public User save(User user) {
		return userRepository.save(user);
	}
}
