package com.diefthyntis.chatop.diefthyntis.io.backtofront;





import lombok.Data;

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
