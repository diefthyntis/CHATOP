package com.diefthyntis.chatop.diefthyntis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;

import com.diefthyntis.chatop.diefthyntis.dto.response.RentalResponse;
import com.diefthyntis.chatop.diefthyntis.exception.RentalNotFoundException;
import com.diefthyntis.chatop.diefthyntis.mapping.RentalMapping;

import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.repository.RentalRepository;
import com.diefthyntis.chatop.diefthyntis.utils.FileUploadUtils;
import com.diefthyntis.chatop.diefthyntis.utils.FileUtils;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class RentalService {
	
	
	@Value("${diefthyntis.store}")
	private String storePlace;

	@Autowired
	private final RentalRepository rentalRepository;
	private final RentalMapping rentalMapping;
	
	
	private final UserService userService;
	
	/*
	public Rental saveFtb(RentalFtb rentalFromFrontEnd) {
		final String filename = FileUtils
				.generateStringFromDate(FileUtils.getExtensionByStringHandling(rentalFromFrontEnd.getNativepicturefilename()).orElse(null));
		
		
		
		final User owner = userService.findByEmail(rentalFromFrontEnd.getEmailaddressowner());
		
		
		final Rental rentalToCreate = new Rental();
		rentalToCreate.setCastlename(rentalFromFrontEnd.getCastlename());
		rentalToCreate.setDescription(rentalFromFrontEnd.getDescription());
		rentalToCreate.setPrice(rentalFromFrontEnd.getPrice());
		rentalToCreate.setSurface(rentalFromFrontEnd.getSurface());
		rentalToCreate.setOwner(owner);
		
		rentalToCreate.setTimepicturename(filename);
		
		
		
		final Rental rentalCreated= rentalRepository.save(rentalToCreate);
		
		final String uploadDir = storePlace + "/" + rentalCreated.getId();
		try {
			FileUploadUtils.saveFile(uploadDir, filename, rentalFromFrontEnd.getPictureobject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
		return rentalCreated;
		
	}
	*/
	
	/*

	public Rental updateFtb(RentalFtb rentalFromFrontEnd) {
		final Rental rentalBeforeUpdate = getRentalById(rentalFromFrontEnd.getId());
		final Rental rentalToUpdate = new Rental();
		rentalToUpdate.setCastlename(rentalFromFrontEnd.getCastlename());
		rentalToUpdate.setDescription(rentalFromFrontEnd.getDescription());
		rentalToUpdate.setPrice(rentalFromFrontEnd.getPrice());
		rentalToUpdate.setSurface(rentalFromFrontEnd.getSurface());
		rentalToUpdate.setOwner(rentalBeforeUpdate.getOwner());
		rentalToUpdate.setTimepicturename(rentalBeforeUpdate.getTimepicturename());
		rentalToUpdate.setId(rentalFromFrontEnd.getId());
		final Rental rentalUpdated= rentalRepository.save(rentalToUpdate);
		return rentalUpdated;
		
	}
		 * 
	 */
	
	
	/*

	public Rental getRentalById(Integer rentalId) {
		return rentalRepository.findById(rentalId).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
	}
		 * 
	 */
	public Rental getRentalById(Integer rentalId) {
		return rentalRepository.findById(rentalId).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
	}
	
	

	public List<Rental> getRentals() {

		return rentalRepository.findAll();
	}

	public List<Rental> getRentalsByUserId(Integer userId) {

		return rentalRepository.findByowner_id(userId);
	}
	
	
	
	/*
	 * Nouvelle version pour déplacer dans le service le traitement métier qui était dans le controller
	 */
	public Rental save(RentalRequest rentalRequest) {
		final Rental rentalToCreate = rentalMapping.mapRentalRequestToRental(rentalRequest);
		
		final User user = userService.findByEmail(rentalRequest.getEmailaddress());
		rentalToCreate.setUser(user);
		
		final String timefilename = FileUtils.generateStringFromDate(FileUtils.getExtensionByStringHandling(rentalRequest.getPicturefilename()).orElse(null));
		rentalToCreate.setPicturefilename(timefilename);
				
		final Rental rentalCreated= rentalRepository.save(rentalToCreate);
		
		final String uploadDir = storePlace + "/" + rentalCreated.getId();
		try {
			FileUploadUtils.saveFile(uploadDir, timefilename, rentalRequest.getPictureobject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
		return rentalCreated;
	}
	
	public Rental update(RentalRequest rentalRequest) {
		final Rental rentalToUpdate = rentalMapping.mapRentalRequestToRental(rentalRequest);
		final User user = userService.findByEmail(rentalRequest.getEmailaddress());
		rentalToUpdate.setUser(user);
		rentalToUpdate.setPicturefilename(rentalRequest.getPicturefilename());
		rentalToUpdate.setId(rentalRequest.getId());
		final Rental rentalUpdated= rentalRepository.save(rentalToUpdate);
		return rentalUpdated;
		
	}



	public List<RentalResponse> getRentalResponseByUserid(Integer userid) {
		List<Rental> rentalList = getRentalsByUserId(userid);
		List<RentalResponse> rentalResponseList = new ArrayList();
		rentalList.stream().forEach(rental -> {
			final RentalResponse rentalResponse = rentalMapping.mapRentalToRentalResponse(rental);

			rentalResponseList.add(rentalResponse);
		});
		return rentalResponseList;
	}



	public List<RentalResponse> getRentalResponseListByEmailaddress(String emailaddress) {
		final User user = userService.findByEmail(emailaddress);
		List<Rental> rentalList = getRentalsByUserId(user.getId());
		List<RentalResponse> rentalResponseList = new ArrayList();
		rentalList.stream().forEach(rental -> {
			final RentalResponse rentalResponse = rentalMapping.mapRentalToRentalResponse(rental);

			rentalResponseList.add(rentalResponse);
		});
		return rentalResponseList;
	}



	public RentalResponse getRentalResponseById(Integer id) {
		final Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
		final RentalResponse rentalResponse = rentalMapping.mapRentalToRentalResponse(rental);
		return rentalResponse;
	}



	

}
