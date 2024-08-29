package com.diefthyntis.chatop.diefthyntis.dto.response;

import lombok.Data;


// version pour s'adapter au front End OCR tordu !!! rentals.rentals
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
