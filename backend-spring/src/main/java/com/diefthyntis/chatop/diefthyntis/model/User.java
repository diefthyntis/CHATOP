package com.diefthyntis.chatop.diefthyntis.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


/*
 CHATOP
 */

@Entity
@Table(name = "users",
uniqueConstraints = {
       @UniqueConstraint(columnNames = "email")
})
@Data
/* @Data annotation de LOMBOK 
 * qui permet de ne pas déclarer les getters et setters qui sont générés à la compilation
 * 
 */
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    
    @NotBlank
    @Size(max = 20)
    private String name;
    
    @NotBlank
    @Size(max = 120)
    private String password;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime created_at;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updated_at;
    
    public User(){
    	updated_at=LocalDateTime.now();
    	created_at = LocalDateTime.now();
    }
    
}
