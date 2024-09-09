package com.diefthyntis.chatop.diefthyntis.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.exception.UserNotFoundException;
import com.diefthyntis.chatop.diefthyntis.io.backtofront.UserResponse;
import com.diefthyntis.chatop.diefthyntis.mapping.UserMapping;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserMapping userMapping;

	public User save(User user) {
		return userRepository.save(user);
	}

	public Boolean existsByEmailAddress(final String emailAddress) {
		return userRepository.existsByEmail(emailAddress);

	}

	public User getUserById(Integer id) {
		Optional<User> optionalUser =  userRepository.findById(id);
		final User user =optionalUser.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		return user; 
	}

	public User findByEmail(String emailaddress) {
		Optional<User> optionalUser = userRepository.findByEmail(emailaddress);
		final User user =optionalUser.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		return user;
	}

	public UserResponse getUserResponseById(Integer id) {
		User user = getUserById(id);
		final UserResponse userResponse = userMapping.mapUserToUserResponse(user);
		return userResponse;
	}

}
