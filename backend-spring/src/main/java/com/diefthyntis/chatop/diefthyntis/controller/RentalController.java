package com.diefthyntis.chatop.diefthyntis.controller;


import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.ServerResponse;
import com.diefthyntis.chatop.diefthyntis.exception.MissingFileException;
import com.diefthyntis.chatop.diefthyntis.mapping.RentalMapping;
import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.service.RentalService;
import com.diefthyntis.chatop.diefthyntis.utils.FileUploadUtils;
import com.diefthyntis.chatop.diefthyntis.utils.FileUtils;
import com.diefthyntis.chatop.diefthyntis.utils.NumberUtils;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @RequiredArgsConstructor permet d'avoir un constructeur avec la dépendance "final" injectée 
 * "dépendance" est en fait une déclaration de classe de type service "service, repository ou mapping
 * @RequiredArgsConstructor permet de ne pas avoir à créer le constructeur chargé d'instancier rentalService et rentalMapping
 */

/*
 * @Slf4j permet d'injecter un loggueur
 */

/*
 * Il y autant de thread que d'utilisateurs connecté
 * L'information de l'utilisateur connecté est disponible dans l'interface Principal
 */



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RentalController {
	private final RentalService rentalService;
	
	@Value("${diefthyntis.store}")
	  private String storePlace;
	
	private final RentalMapping rentalMapping;
	
	@PostMapping("/rentals")
    public ResponseEntity<ServerResponse> create(final @RequestPart("name") String name,
            final @RequestPart("surface") String surface,
            final @RequestPart("price") String price,
            final @RequestPart("picture") MultipartFile picture,
            final @RequestPart("description") String description,final Principal principal) throws IOException, java.io.IOException {
		log.info("debut de la creation de rental");
		final String fileName = StringUtils.cleanPath(Optional.ofNullable(picture.getOriginalFilename())
                .orElseThrow(()-> new MissingFileException("The uploaded file is required but is missing.")));
		String emailAddressOwner = principal.getName();
		
		final String fName = FileUtils.generateStringFromDate(FileUtils.getExtensionByStringHandling(fileName).orElse(null));
		
		/*
		 * l'objet RentalRequest est posté par le FrontEnd et reçu par le controller
		 */
		final RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setPrice(NumberUtils.convertToFloat(price));
		rentalRequest.setDescription(description);
		rentalRequest.setSurface(NumberUtils.convertToFloat(surface));
		rentalRequest.setPicture(fName);
		rentalRequest.setEmailAddressOwner(emailAddressOwner);
		final Rental rental = rentalMapping.mapRentalRequestToRental(rentalRequest);
		final Rental rentalCreated =rentalService.save(rental);
		
		final String uploadDir = storePlace + "/" + rentalCreated.getId();
        FileUploadUtils.saveFile(uploadDir, fName, picture);
		
		
		return ResponseEntity.ok(new ServerResponse("Rental created !"));
      
    }
	
	
}
