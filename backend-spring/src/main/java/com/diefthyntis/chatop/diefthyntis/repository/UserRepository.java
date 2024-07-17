package com.diefthyntis.chatop.diefthyntis.repository;

import org.springframework.stereotype.Repository;

import com.diefthyntis.chatop.diefthyntis.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
