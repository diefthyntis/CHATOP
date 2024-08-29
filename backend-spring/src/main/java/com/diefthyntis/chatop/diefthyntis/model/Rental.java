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

/*
 * L'ordre des annotations n'a pas d'importance
 * L'interpréteur d'annotation les traites dans l'ordre qui vient sans importance
 */
@Data
@Entity
@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 20)
	private String name;

	@Column(name = "surface")
	private Float surface;

	@Column(name = "price")
	private Float price;

	@Column(name = "picture")
	private String picture;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User owner;

	@Column(name = "created_at")
	private java.time.LocalDateTime createdAt;

	@Column(name = "updated_at")
	private java.time.LocalDateTime updatedAt;

	public Rental() {
		updatedAt = LocalDateTime.now();
		createdAt = LocalDateTime.now();
	}
}
