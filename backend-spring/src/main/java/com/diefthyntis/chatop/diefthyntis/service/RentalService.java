package com.diefthyntis.chatop.diefthyntis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.exception.RentalNotFoundException;

import com.diefthyntis.chatop.diefthyntis.model.Rental;
import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.repository.RentalRepository;
import com.diefthyntis.chatop.diefthyntis.utils.NumberUtils;

import lombok.RequiredArgsConstructor;


/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class RentalService {
	
	@Autowired
	private final RentalRepository rentalRepository;
	
	
	public Rental save(Rental rental) {
		return rentalRepository.save(rental);
	}
	
	public Rental getRentalById(Integer rentalId) {
		return rentalRepository.findById(rentalId).orElseThrow(() -> new RentalNotFoundException("Rental Not Found"));
	}
	

	 
	public List<Rental> getRentals() {
		// TODO Auto-generated method stub
		return rentalRepository.findAll();
		}
	 
	
	 public List<Rental> getRentalsByUserId(Integer userId) {
			// TODO Auto-generated method stub
			return rentalRepository.findByowner_id(userId);
			}
	
	/*
	public Integer update(Rental rental) {
		
		
		return rentalRepository.update(rental.getId().toString(),rental.getName(),rental.getSurface().toString(),rental.getPrice().toString(),rental.getDescription());
		
	}
	*/
	
	
	
	
}
