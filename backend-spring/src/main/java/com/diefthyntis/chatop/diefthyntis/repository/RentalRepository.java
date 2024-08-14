package com.diefthyntis.chatop.diefthyntis.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diefthyntis.chatop.diefthyntis.model.Rental;




@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
