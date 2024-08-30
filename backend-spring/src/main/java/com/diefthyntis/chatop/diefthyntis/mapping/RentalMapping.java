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
	
	/*
	public Rental mapRentalRequestToRentalForSave(RentalRequest rentalRequest) {
		final Rental rental = MapRentalToRentalRequest(rentalRequest);
		rental.setPicture(rentalRequest.getPicture().getName());
		return rental;
	}
	*/

/*
	public RentalResponse mapRentalToRentalResponse(Rental rental) {
		final RentalResponse rentalResponse = new RentalResponse();
		rentalResponse.setId(rental.getId());
		rentalResponse.setDescription(rental.getDescription());
		rentalResponse.setOwner_id(rental.getOwner().getId());
		rentalResponse.setName(rental.getName());
		rentalResponse.setPicture(rental.getPicture());
		rentalResponse.setPrice(rental.getPrice());
		rentalResponse.setSurface(rental.getSurface());
		rentalResponse.setCreated_at(DateUtils.convertLocalDateToString(rental.getCreatedAt()));
		rentalResponse.setUpdated_at(DateUtils.convertLocalDateToString(rental.getCreatedAt()));
		// TODO Auto-generated method stub
		return rentalResponse;
	}
	*/
	
	/*
	public Rental mapRentalRequestToRentalForUpdate(RentalRequest rentalRequest) {
		final Rental rental = MapRentalToRentalRequest(rentalRequest);
		return rental;
	}
	*/

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


	public RentalDto mapRentalToRentalDto(Rental rental) {
		final RentalDto rentalDto = new RentalDto();
		rentalDto.setId(rental.getId());
		rentalDto.setDescription(rental.getDescription());
		rentalDto.setOwner_id(rental.getOwner().getId());
		rentalDto.setName(rental.getName());
		final String urlPicture="http://localhost:3001/api/images/"+rental.getId()+"/"+rental.getPicture();
		rentalDto.setPicture(urlPicture);
		rentalDto.setPrice(rental.getPrice());
		rentalDto.setSurface(rental.getSurface());
		rentalDto.setCreated_at(DateUtils.convertLocalDateToString(rental.getCreatedAt()));
		rentalDto.setUpdated_at(DateUtils.convertLocalDateToString(rental.getCreatedAt()));
		// TODO Auto-generated method stub
		return rentalDto;
	}
}
