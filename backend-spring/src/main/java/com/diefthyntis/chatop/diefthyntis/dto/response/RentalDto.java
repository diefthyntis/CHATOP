package com.diefthyntis.chatop.diefthyntis.dto.response;

import lombok.Data;

/*
 * Cette classe va être instanciée plusieurs fois pour alimenter une liste contenu dans 
 * une instance de la classe RentalResponse 
 */
@Data
public class RentalDto {
	private Integer id;
	private String name;
	private Float surface;
	private Float price;
	private String picture;
	private String description;
	private Integer owner_id;
	private String created_at;
	private String updated_at;
}
