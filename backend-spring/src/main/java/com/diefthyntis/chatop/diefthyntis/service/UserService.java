package com.diefthyntis.chatop.diefthyntis.service;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.exception.UserNotFoundException;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public Boolean existsByEmailAddress(final String emailAddress) {
		return userRepository.existsByEmail(emailAddress);

	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}

	public User findByEmail(String emailAddress) {
		return userRepository.findByEmail(emailAddress).orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}

}
