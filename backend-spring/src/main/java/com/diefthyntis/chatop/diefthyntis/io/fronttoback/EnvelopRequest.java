package com.diefthyntis.chatop.diefthyntis.io.fronttoback;

import lombok.Data;

@Data
public class EnvelopRequest {
	private String message;
	private String user_id;
	private String rental_id;
}
