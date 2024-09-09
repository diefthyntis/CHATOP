package com.diefthyntis.chatop.diefthyntis.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.io.backtofront.RentalDto;
import com.diefthyntis.chatop.diefthyntis.io.fronttoback.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.toolbox.DateUtils;
import com.diefthyntis.chatop.diefthyntis.toolbox.NumberUtils;

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
	

	public Rental mapRentalRequestToRental(RentalRequest rentalRequest) {
		final Rental rental = new Rental();
		rental.setCastlename(rentalRequest.getCastlename());
		rental.setSurface(NumberUtils.convertToFloat(rentalRequest.getSurface()));
		rental.setPrice(NumberUtils.convertToFloat(rentalRequest.getPrice()));
		rental.setDescription(rentalRequest.getDescription());
		return rental;
	}

	public RentalDto mapRentalToRentalBackToFront(Rental rental) {
		final RentalDto RentalBackToFront = new RentalDto();
		RentalBackToFront.setId(rental.getId());
		RentalBackToFront.setDescription(rental.getDescription());
		RentalBackToFront.setOwner_id(rental.getOwner().getId());
		RentalBackToFront.setName(rental.getCastlename());
		RentalBackToFront.setPrice(rental.getPrice());
		RentalBackToFront.setSurface(rental.getSurface());
		
		RentalBackToFront.setCreated_at(DateUtils.convertLocalDateToString(rental.getCreatedat()));
		RentalBackToFront.setUpdated_at(DateUtils.convertLocalDateToString(rental.getCreatedat()));
		
		return RentalBackToFront;
	}
}
