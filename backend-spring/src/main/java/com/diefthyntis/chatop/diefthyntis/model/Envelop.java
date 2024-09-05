package com.diefthyntis.chatop.diefthyntis.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "envelop")
@Data
public class Envelop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 120)
	@Column(name = "word")
	private String message;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "rental_id", referencedColumnName = "id")
	private Rental rental;

	@Column(name = "created_at")
	private java.time.LocalDateTime createdAt;

	@Column(name = "updated_at")
	private java.time.LocalDateTime updatedAt;

	public Envelop() {
		updatedAt = LocalDateTime.now();
		createdAt = LocalDateTime.now();
	}
}
