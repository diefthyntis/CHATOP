package com.diefthyntis.chatop.diefthyntis.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.diefthyntis.chatop.diefthyntis.model.Rental;




@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
	
	
	List<Rental> findByowner_id(Integer ownerId);
	
	/*
	// Exemple de JPQL
	
	
    @Query("SELECT r FROM Rental r WHERE r.user.id = :userId")
    List<Rental> findRentalsByUserId(@Param("userId") Integer userId);
    
    
    
    public List<Rental> findRentalsByUserId(Integer userId) {
        String jpql = "SELECT r FROM Rental r WHERE r.user.id = :userId";
        return entityManager.createQuery(jpql, Rental.class)
                            .setParameter("userId", userId)
                            .getResultList();
    }
    */
}
