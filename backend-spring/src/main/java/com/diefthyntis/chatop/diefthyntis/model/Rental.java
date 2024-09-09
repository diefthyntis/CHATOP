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
 * L'interpréteur d'annotation les traite dans l'ordre qui vient sans importance
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
	@Column(name = "name")
	private String castlename;

	@Column(name = "surface")
	private Float surface;

	@Column(name = "price")
	private Float price;

	@Column(name = "picture")
	private String picturefilename;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User user;

	@Column(name = "created_at")
	private java.time.LocalDateTime createdat;

	@Column(name = "updated_at")
	private java.time.LocalDateTime updatedat;

	public Rental() {
		updatedat = LocalDateTime.now();
		createdat = LocalDateTime.now();
	}
}
