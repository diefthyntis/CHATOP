package com.diefthyntis.chatop.diefthyntis.dto.response;

import lombok.Data;

@Data
public class UserResponse {
	private Integer id;
	private String name;
	private String email;
	private String created_at;
	private String updated_at;

}
