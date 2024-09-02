package com.diefthyntis.chatop.diefthyntis.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.diefthyntis.chatop.diefthyntis.dto.request.RentalRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalDto;
import com.diefthyntis.chatop.diefthyntis.dto.response.RentalResponse;
import com.diefthyntis.chatop.diefthyntis.dto.response.ServerResponse;

import com.diefthyntis.chatop.diefthyntis.exception.MissingFileException;
import com.diefthyntis.chatop.diefthyntis.mapping.RentalMapping;

import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.service.RentalService;
import com.diefthyntis.chatop.diefthyntis.service.UserService;
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
			final @RequestPart("surface") String surface, final @RequestPart("price") String price,
			final @RequestPart("picture") MultipartFile picture, final @RequestPart("description") String description,
			final Principal principal) throws IOException, java.io.IOException {
		log.info("debut de la creation de rental");

		/*
		 * 
		 * 
		 * Dans ce contexte RentalController, les données envoyées par ANGULAR sont
		 * encapsulées dans une structure de données de type "form-data" et non pas dans
		 * une structure de données de type "Json", qui est une alternative à
		 * "form-data" Ce type de structure de données "form-data" implique de traiter
		 * les champs un par un. "form-data" est utilisé dans ce contexte plutot que
		 * Json car il y a un fichier image à transporter, ce qui est impossible avec
		 * Json
		 * 
		 * 
		 * Si l'objet reçu était un objet Json, on aurait du mettre comme paramètre de
		 * la méthode: "final @RequestBody RentalRequest rentalRequest" La
		 * désérialisation consiste à transformer un objet json en objet java
		 *
		 */

		final String filepath = StringUtils.cleanPath(Optional.ofNullable(picture.getOriginalFilename())
				.orElseThrow(() -> new MissingFileException("The uploaded file is required but is missing.")));
		final String filename = FileUtils
				.generateStringFromDate(FileUtils.getExtensionByStringHandling(filepath).orElse(null));

		String emailAddressOwner = principal.getName();

		/*
		 * l'objet RentalRequest est posté par le FrontEnd et reçu par le controller
		 */
		final RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setPrice(NumberUtils.convertToFloat(price));
		rentalRequest.setDescription(description);
		rentalRequest.setSurface(NumberUtils.convertToFloat(surface));
		rentalRequest.setPicture(filename);
		rentalRequest.setEmailAddressOwner(emailAddressOwner);
		final Rental rental = rentalMapping.mapRentalRequestToRentalForSave(rentalRequest);

		/*
		 * on est obligé de récupérer le rental crée pour obtenir l'id qui sert à
		 * constuire le nom de l'image
		 */
		final Rental rentalCreated = rentalService.save(rental);

		final String uploadDir = storePlace + "/" + rentalCreated.getId();
		FileUploadUtils.saveFile(uploadDir, filename, picture);

		return ResponseEntity.ok(new ServerResponse("Rental created !"));

	}

	@GetMapping("/rentals/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RentalDto getRentalById(@PathVariable Integer id) {
		Rental rental = rentalService.getRentalById(id);
		return rentalMapping.mapRentalToRentalDto(rental);
	}

	private final UserService userService;

	@GetMapping("/rentals")
	public RentalResponse getRentals(final Principal principal) {
		String emailAddressUser = principal.getName();
		final User user = userService.findByEmail(emailAddressUser);
		List<Rental> rentals = rentalService.getRentalsByUserId(user.getId());
		RentalResponse rentalResponse = new RentalResponse();
		List<RentalDto> rentalDtos = new ArrayList();
		rentals.stream().forEach(rental -> {
			final RentalDto rentalDto = rentalMapping.mapRentalToRentalDto(rental);

			rentalDtos.add(rentalDto);
		});
		rentalResponse.setRentals(rentalDtos);
		return rentalResponse;
	}

	@PutMapping("/rentals/{id}")
	public ResponseEntity<ServerResponse> update(@PathVariable Integer id, final @RequestPart("name") String name,
			final @RequestPart("surface") String surface, final @RequestPart("price") String price,
			final @RequestPart("description") String description) throws IOException, java.io.IOException {
		log.info("Début de la modification de rental");

		/*
		 * l'objet RentalRequest est posté par le FrontEnd et reçu par le controller
		 */

		final RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setPrice(NumberUtils.convertToFloat(price));
		rentalRequest.setDescription(description);
		rentalRequest.setSurface(NumberUtils.convertToFloat(surface));
		final Rental rental = rentalService.getRentalById(id);
		final Rental rentalResultMapping = rentalMapping.mapRentalRequestToRentalForUpdate(rental, rentalRequest);

		rentalService.save(rentalResultMapping);

		return ResponseEntity.ok(new ServerResponse("Rental updated !"));

	}

}
