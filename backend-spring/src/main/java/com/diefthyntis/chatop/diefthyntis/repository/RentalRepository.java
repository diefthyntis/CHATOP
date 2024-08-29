package com.diefthyntis.chatop.diefthyntis.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.diefthyntis.chatop.diefthyntis.model.Rental;





@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
	
	
	List<Rental> findByowner_id(Integer ownerId);
}