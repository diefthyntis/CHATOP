package com.diefthyntis.chatop.diefthyntis.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.dto.request.MessageRequest;
import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.UserResponse;
import com.diefthyntis.chatop.diefthyntis.model.Message;
import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.service.RentalService;
import com.diefthyntis.chatop.diefthyntis.service.UserService;
import com.diefthyntis.chatop.diefthyntis.utils.DateUtils;
import com.diefthyntis.chatop.diefthyntis.utils.NumberUtils;

import lombok.RequiredArgsConstructor;

/*
* RentalMapping propose des méthodes qui permettent de mapper un objet RentalRequest vers Rental
* L'objet RentalRequest est posté par le FrontEnd et reçu par le controller
*/
/*
* Grace à l'annotation @RequiredArgsConstructor, il n'y pas besoin d'instancier userService
*/
@Component
@RequiredArgsConstructor
public class MessageMapping {
	private final UserService userService;
	private final RentalService rentalService;
	public Message mapMessageRequestToMessage(MessageRequest messageRequest)
	{
		final Message message = new Message();
		message.setMessage(messageRequest.getMessage());
		User owner = userService.getUserById(NumberUtils.convertToNumeric(messageRequest.getUser_id()));
		Rental rental=rentalService.
		message.setUser(null);
		return Message;
	}
	public Rental mapRentalRequestToRental(RentalRequest rentalRequest) {
		final Rental rental= new Rental();
		
		rental.setName(rentalRequest.getName());
		rental.setSurface(rentalRequest.getSurface());
		rental.setPrice(rentalRequest.getPrice());
		rental.setPicture(rentalRequest.getPicture());
		rental.setDescription(rentalRequest.getDescription());
		User owner = userService.findByEmail(rentalRequest.getEmailAddressOwner());
		rental.setOwner(owner);
		return rental;
	}
	
	public UserResponse mapUserToUserResponse(User user) {
		final UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());
		userResponse.setCreated_at(DateUtils.convertLocalDateToString(user.getCreatedAt()));
		userResponse.setUpdated_at(DateUtils.convertLocalDateToString(user.getUpdatedAt()));
		return userResponse;
	}
}
