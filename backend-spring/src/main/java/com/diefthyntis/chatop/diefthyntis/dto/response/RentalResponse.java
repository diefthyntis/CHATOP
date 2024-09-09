package com.diefthyntis.chatop.diefthyntis.dto.response;

import java.util.List;

import com.diefthyntis.chatop.diefthyntis.model.Rental;

import lombok.Data;

@Data
public class RentalResponse {
	
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
