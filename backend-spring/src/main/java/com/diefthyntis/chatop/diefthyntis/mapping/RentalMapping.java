package com.diefthyntis.chatop.diefthyntis.mapping;


import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.service.UserService;

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
public class RentalMapping {
	private final UserService userService;
	
	
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
}
