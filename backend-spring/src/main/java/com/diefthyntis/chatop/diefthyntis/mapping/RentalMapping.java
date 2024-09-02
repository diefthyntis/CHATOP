package com.diefthyntis.chatop.diefthyntis.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalDto;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalResponse;
import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.service.UserService;
import com.diefthyntis.chatop.diefthyntis.utils.DateUtils;

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

	public Rental mapRentalRequestToRentalForSave(RentalRequest rentalRequest) {
		final Rental rental = new Rental();
		final Rental rentalResultMapping = mapRentalRequestToRental(rental, rentalRequest);
		rentalResultMapping.setPicture(rentalRequest.getPicture());
		User owner = userService.findByEmail(rentalRequest.getEmailAddressOwner());
		rentalResultMapping.setOwner(owner);
		return rentalResultMapping;
	}

	public Rental mapRentalRequestToRentalForUpdate(final Rental rental, final RentalRequest rentalRequest) {
		return mapRentalRequestToRental(rental, rentalRequest);

	}

	public Rental mapRentalRequestToRental(final Rental rental, final RentalRequest rentalRequest) {

		rental.setName(rentalRequest.getName());
		rental.setSurface(rentalRequest.getSurface());
		rental.setPrice(rentalRequest.getPrice());
		rental.setDescription(rentalRequest.getDescription());

		return rental;
	}

	public RentalDto mapRentalToRentalDto(Rental rental) {
		final RentalDto rentalDto = new RentalDto();
		rentalDto.setId(rental.getId());
		rentalDto.setDescription(rental.getDescription());
		rentalDto.setOwner_id(rental.getOwner().getId());
		rentalDto.setName(rental.getName());
		final String urlPicture = "http://localhost:3001/api/images/" + rental.getId() + "/" + rental.getPicture();
		rentalDto.setPicture(urlPicture);
		rentalDto.setPrice(rental.getPrice());
		rentalDto.setSurface(rental.getSurface());
		rentalDto.setCreated_at(DateUtils.convertLocalDateToString(rental.getCreatedAt()));
		rentalDto.setUpdated_at(DateUtils.convertLocalDateToString(rental.getCreatedAt()));

		return rentalDto;
	}
}
