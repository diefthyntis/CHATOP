package com.diefthyntis.chatop.diefthyntis.controller;




import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.diefthyntis.chatop.diefthyntis.dto.request.EnvelopRequest;
import com.diefthyntis.chatop.diefthyntis.dto.response.ServerResponse;
import com.diefthyntis.chatop.diefthyntis.exception.MissingFileException;
import com.diefthyntis.chatop.diefthyntis.mapping.EnvelopMapping;
import com.diefthyntis.chatop.diefthyntis.model.Envelop;
import com.diefthyntis.chatop.diefthyntis.service.EnvelopService;
import com.diefthyntis.chatop.diefthyntis.utils.FileUploadUtils;
import com.diefthyntis.chatop.diefthyntis.utils.FileUtils;
import com.diefthyntis.chatop.diefthyntis.utils.NumberUtils;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @RequiredArgsConstructor permet d'avoir un constructeur avec la dépendance "final" injectée 
 * "dépendance" est en fait une déclaration de classe de type service "service, repository ou mapping
 * @RequiredArgsConstructor permet de ne pas avoir à créer le constructeur chargé d'instancier envelopService et envelopMapping
 */

/*
 * @Slf4j permet d'injecter un loggueur
 */

/*
 * Il y autant de thread que d'utilisateurs connecté
 * L'information de l'utilisateur connecté est disponible dans l'interface Principal
 */



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class EnvelopController {
	private final EnvelopService envelopService;
	private final EnvelopMapping envelopMapping;
	
	@PostMapping("/messages")
    public ResponseEntity<ServerResponse> create(final @RequestBody EnvelopRequest envelopRequest) throws IOException, java.io.IOException {
		log.info("debut de la creation de envelop");
			
		/*
		 * l'objet EnvelopRequest est posté par le FrontEnd et reçu par le controller
		 */
		
		final Envelop envelop = envelopMapping.mapEnvelopRequestToEnvelop(envelopRequest);
		envelopService.save(envelop);
			
		
		return ResponseEntity.ok(new ServerResponse("Message send with success"));
      
    }
	
	
}
