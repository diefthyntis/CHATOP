package com.diefthyntis.chatop.diefthyntis.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
 * l'objet RentalRequest est posté par le FrontEnd et reçu par le controller
 */
@Data
public class RentalRequest {
	private String name;
	private float surface;
	private float price;
	private String picture;
	private String description;
	private String emailAddressOwner;
	
}
