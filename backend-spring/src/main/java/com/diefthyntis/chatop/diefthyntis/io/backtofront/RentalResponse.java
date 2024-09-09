package com.diefthyntis.chatop.diefthyntis.io.backtofront;

import java.util.List;

import com.diefthyntis.chatop.diefthyntis.model.Rental;

import lombok.Data;
@Data
public class RentalResponse {
	private List<RentalDto> rentals;
}
