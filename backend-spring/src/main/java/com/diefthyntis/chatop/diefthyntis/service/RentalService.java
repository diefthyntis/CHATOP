package com.diefthyntis.chatop.diefthyntis.service;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.exception.RentalNotFoundException;

import com.diefthyntis.chatop.diefthyntis.model.Rental;

import com.diefthyntis.chatop.diefthyntis.repository.RentalRepository;


import lombok.RequiredArgsConstructor;


/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepository rentalRepository;
	
	public Rental save(Rental rental) {
		return rentalRepository.save(rental);
	}
	
	public Rental getRentalById(Integer id) {
		return rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
	}
	
	
}
