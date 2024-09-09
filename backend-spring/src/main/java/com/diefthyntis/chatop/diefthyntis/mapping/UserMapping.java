package com.diefthyntis.chatop.diefthyntis.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.io.backtofront.UserResponse;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.toolbox.DateUtils;

@Component
public class UserMapping {
	public UserResponse mapUserToUserResponse(User user) {
		final UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());
		userResponse.setCreated_at(DateUtils.convertLocalDateToString(user.getCreated_at()));
		userResponse.setUpdated_at(DateUtils.convertLocalDateToString(user.getUpdated_at()));
		return userResponse;
	}
}
