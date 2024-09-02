package com.diefthyntis.chatop.diefthyntis.repository;

import org.springframework.stereotype.Repository;

import com.diefthyntis.chatop.diefthyntis.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String emailAddress);

	Boolean existsByEmail(String emailAddress);

}
