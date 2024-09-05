package com.diefthyntis.chatop.diefthyntis.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;


import lombok.Data;

/*
 * Cette classe va être instanciée plusieurs fois pour alimenter une liste contenu dans 
 * une instance de la classe RentalResponse 
 */
@Data
public class RentalFtb {
	
	
	private MultipartFile pictureobject;
	private String urlpicture;
	private String nativepicturefilename;
	private User owner;
	private String creationdate;
	private String modificationdate;
	private String emailaddressowner;
	private String castlename;
	private Float surface;
	private Float price;
	private Integer id;
	private String description;
	private String timepicturename;
	
}
