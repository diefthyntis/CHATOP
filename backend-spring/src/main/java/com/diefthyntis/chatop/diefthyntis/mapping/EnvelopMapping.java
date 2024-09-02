package com.diefthyntis.chatop.diefthyntis.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.dto.request.EnvelopRequest;
import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.UserResponse;
import com.diefthyntis.chatop.diefthyntis.model.Envelop;
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

/*
 * l'objet EnvelopRequest est posté par le FrontEnd et reçu par le controller
 */
@Component
@RequiredArgsConstructor
public class EnvelopMapping {
	private final UserService userService;
	private final RentalService rentalService;

	public Envelop mapEnvelopRequestToEnvelop(EnvelopRequest envelopRequest) {
		final Envelop envelop = new Envelop();
		envelop.setWord(envelopRequest.getWord());
		User user = userService.getUserById(NumberUtils.convertToInteger(envelopRequest.getUser_id()));
		envelop.setUser(user);
		Rental rental = rentalService.getRentalById(NumberUtils.convertToInteger(envelopRequest.getRental_id()));
		envelop.setRental(rental);

		return envelop;
	}

}
