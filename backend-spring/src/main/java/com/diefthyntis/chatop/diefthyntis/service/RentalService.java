package com.diefthyntis.chatop.diefthyntis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.dto.request.RentalFtb;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalBtf;
import com.diefthyntis.chatop.diefthyntis.exception.RentalNotFoundException;

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
	
	
	private final UserService userService;
	

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
	
	
	

	public Rental getRentalById(Integer rentalId) {
		return rentalRepository.findById(rentalId).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
	}

	public List<Rental> getRentals() {

		return rentalRepository.findAll();
	}

	public List<Rental> getRentalsByUserId(Integer userId) {

		return rentalRepository.findByowner_id(userId);
	}
	


}
