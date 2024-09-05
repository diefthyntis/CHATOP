package com.diefthyntis.chatop.diefthyntis.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
 * l'objet RentalRequest est posté par le FrontEnd et reçu par le controller
 */
@Data
public class RentalRequest {
	private String castlename;
	private String surface;
	private String price;
	private MultipartFile pictureobject;
	private String description;
	private String emailAddressOwner;
	private String nativepicturefilename;
	private Integer id;
}

