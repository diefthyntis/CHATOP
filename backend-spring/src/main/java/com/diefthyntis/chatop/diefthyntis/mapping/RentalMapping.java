package com.diefthyntis.chatop.diefthyntis.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.chatop.diefthyntis.dto.request.RentalFtb;
import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalBtf;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalResponse;
import com.diefthyntis.chatop.diefthyntis.model.Rental;

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
public class RentalMapping {
	


	/*
	
	public RentalFtb mapRentalRequestToRentalFtbForSave(RentalRequest rentalRequest) {
		final RentalFtb rentalFtb = new RentalFtb();
		rentalFtb.setCastlename(rentalRequest.getCastlename());
		rentalFtb.setSurface(NumberUtils.convertToFloat(rentalRequest.getSurface()));
		rentalFtb.setPrice(NumberUtils.convertToFloat(rentalRequest.getPrice()));
		rentalFtb.setDescription(rentalRequest.getDescription());
		rentalFtb.setPictureobject(rentalRequest.getPictureobject());
		rentalFtb.setEmailaddressowner(rentalRequest.getEmailAddressOwner());
		rentalFtb.setNativepicturefilename(rentalRequest.getNativepicturefilename());
		return rentalFtb;
	}
	*/

	public RentalFtb mapRentalRequestToRentalFtbForUpdate(final RentalRequest rentalRequest) {
		final RentalFtb rentalFtb = new RentalFtb();
		rentalFtb.setCastlename(rentalRequest.getCastlename());
		rentalFtb.setSurface(NumberUtils.convertToFloat(rentalRequest.getSurface()));
		rentalFtb.setPrice(NumberUtils.convertToFloat(rentalRequest.getPrice()));
		rentalFtb.setDescription(rentalRequest.getDescription());
		rentalFtb.setId(rentalRequest.getId());
		return rentalFtb;

	}
	

	/*
	public RentalBtf mapRentalToRentalBtf(Rental rental) {
		final RentalBtf rentalBtf = new RentalBtf();
		rentalBtf.setId(rental.getId());
		rentalBtf.setDescription(rental.getDescription());
		rentalBtf.setOwner_id(rental.getOwner().getId());
		rentalBtf.setName(rental.getCastlename());
		final String urlPicture = "http://localhost:3001/api/images/" + rental.getId() + "/" + rental.getTimepicturename();
		rentalBtf.setPicture(urlPicture);
		rentalBtf.setPrice(rental.getPrice());
		rentalBtf.setSurface(rental.getSurface());
		
		rentalBtf.setCreated_at(DateUtils.convertLocalDateToString(rental.getCreatedat()));
		rentalBtf.setUpdated_at(DateUtils.convertLocalDateToString(rental.getCreatedat()));
		
		return rentalBtf;
	}
	*/
	
	/*
	 * 09/09/2024
	 * Nouvelle méthode pour déplacer le traitement métier dans le service
	 */
	public Rental mapRentalRequestToRental(RentalRequest rentalRequest) {
		final Rental rental = new Rental();
		rental.setCastlename(rentalRequest.getCastlename());
		rental.setSurface(NumberUtils.convertToFloat(rentalRequest.getSurface()));
		rental.setPrice(NumberUtils.convertToFloat(rentalRequest.getPrice()));
		rental.setDescription(rentalRequest.getDescription());
		return rental;
	}

	/*
	 * 09/09/2024
	 */
	public RentalResponse mapRentalToRentalResponse(Rental rental) {
		final RentalResponse rentalResponse = new RentalResponse();
		rentalResponse.setId(rental.getId());
		rentalResponse.setDescription(rental.getDescription());
		rentalResponse.setOwner_id(rental.getUser().getId());
		rentalResponse.setName(rental.getCastlename());
		
		final String urlPicture = "http://localhost:3001/api/images/" + rental.getId() + "/" + rental.getPicturefilename();
		rentalResponse.setPicture(urlPicture);
		
		rentalResponse.setPicture(rental.getPicturefilename());
		
		rentalResponse.setPrice(rental.getPrice());
		rentalResponse.setSurface(rental.getSurface());
		
		rentalResponse.setCreated_at(DateUtils.convertLocalDateToString(rental.getCreatedat()));
		rentalResponse.setUpdated_at(DateUtils.convertLocalDateToString(rental.getCreatedat()));
		
		return rentalResponse;
	}
}
