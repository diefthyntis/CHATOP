package com.diefthyntis.chatop.diefthyntis.dto.response;

import java.util.List;

import com.diefthyntis.chatop.diefthyntis.model.Rental;

import lombok.Data;
@Data
public class ManyRentalResponse {
	private List<RentalResponse> RentalResponseList;
}
