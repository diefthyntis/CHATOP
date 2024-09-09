package com.diefthyntis.chatop.diefthyntis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.exception.RentalNotFoundException;
import com.diefthyntis.chatop.diefthyntis.io.backtofront.RentalDto;
import com.diefthyntis.chatop.diefthyntis.io.fronttoback.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.mapping.RentalMapping;
import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.repository.RentalRepository;
import com.diefthyntis.chatop.diefthyntis.toolbox.FileUploadUtils;
import com.diefthyntis.chatop.diefthyntis.toolbox.FileUtils;

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
	
	
	
	
	

	public Rental getRentalById(Integer rentalId) {
		return rentalRepository.findById(rentalId).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
	}
	
	

	public List<Rental> getRentals() {

		return rentalRepository.findAll();
	}

	public List<Rental> getRentalsByUserId(Integer userId) {

		return rentalRepository.findByowner_id(userId);
	}
	
	
	public Rental save(RentalRequest rentalRequest) {
		final Rental rentalToCreate = rentalMapping.mapRentalRequestToRental(rentalRequest);
		
		final User user = userService.findByEmail(rentalRequest.getEmailaddress());
		rentalToCreate.setOwner(user);
		
		final String timefilename = FileUtils.generateStringFromDate(FileUtils.getExtensionByStringHandling(rentalRequest.getPicturefilename()).orElse(null));
		rentalToCreate.setPicturefilename(timefilename);
				
		final Rental rentalCreated= rentalRepository.save(rentalToCreate);
		
		final String uploadDir = storePlace + "/" + rentalCreated.getId();
		try {
			FileUploadUtils.saveFile(uploadDir, timefilename, rentalRequest.getPictureobject());
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (java.io.IOException e) {
			
			e.printStackTrace();
		}
		

	
		return rentalCreated;
	}
	
	
	public String MakeUrlPicture(Integer rentalid,String picturefilnename) {
		final String urlPicture = "http://localhost:3001/api/images/" + rentalid + "/" + picturefilnename;
		return urlPicture;
		
	}
	
	public Rental update(RentalRequest rentalRequest) {
		final Rental rentalToUpdate = rentalMapping.mapRentalRequestToRental(rentalRequest);
		final Rental rentalFromDatabase = getRentalById(rentalRequest.getId());
		rentalToUpdate.setOwner(rentalFromDatabase.getOwner());
		rentalToUpdate.setPicturefilename(rentalFromDatabase.getPicturefilename());
		rentalToUpdate.setId(rentalRequest.getId());
		final Rental rentalUpdated= rentalRepository.save(rentalToUpdate);
		return rentalUpdated;
		
	}



	public List<RentalDto> getRentalBackToFrontByUserid(Integer userid) {
		List<Rental> rentalList = getRentalsByUserId(userid);
		List<RentalDto> RentalBackToFrontList = new ArrayList();
		rentalList.stream().forEach(rental -> {
			final RentalDto RentalBackToFront = rentalMapping.mapRentalToRentalBackToFront(rental);
			final String urlPicture=MakeUrlPicture(RentalBackToFront.getId(),RentalBackToFront.getPicture());
			RentalBackToFront.setPicture(urlPicture);
			RentalBackToFrontList.add(RentalBackToFront);
		});
		return RentalBackToFrontList;
	}



	public List<RentalDto> getRentalBackToFrontListByEmailaddress(String emailaddress) {
		final User user = userService.findByEmail(emailaddress);
		List<Rental> rentalList = getRentalsByUserId(user.getId());
		List<RentalDto> RentalBackToFrontList = new ArrayList();
		rentalList.stream().forEach(rental -> {
			final RentalDto RentalBackToFront = rentalMapping.mapRentalToRentalBackToFront(rental);
			final String urlPicture=MakeUrlPicture(RentalBackToFront.getId(),rental.getPicturefilename());
			RentalBackToFront.setPicture(urlPicture);
			RentalBackToFrontList.add(RentalBackToFront);
		});
		return RentalBackToFrontList;
	}
	



	public RentalDto getRentalBackToFrontById(Integer id) {
		final Rental rentalFromDatabase = rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
		final RentalDto RentalBackToFront = rentalMapping.mapRentalToRentalBackToFront(rentalFromDatabase);
		final String urlPicture=MakeUrlPicture(RentalBackToFront.getId(),rentalFromDatabase.getPicturefilename());
		RentalBackToFront.setPicture(urlPicture);
		return RentalBackToFront;
	}



	

}
