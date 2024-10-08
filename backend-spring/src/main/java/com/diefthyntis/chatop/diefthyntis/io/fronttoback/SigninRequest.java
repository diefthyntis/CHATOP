package com.diefthyntis.chatop.diefthyntis.io.fronttoback;

import lombok.Data;

/*
 * le nom des champs de cette classe est imposé par le nom des champs du fichier Json créé
 * lorsque l'on clique sur le bouton Submit
 * Exemple avec la route localhost:3001/api/auth/login
 * {
    "email": "jack@tortuga.com",
    "password": "jack"
	}
 */
@Data
public class SigninRequest {
	private String email;
	private String password;

}
