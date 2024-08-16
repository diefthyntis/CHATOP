package com.diefthyntis.chatop.diefthyntis.service;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.model.Envelop;
import com.diefthyntis.chatop.diefthyntis.repository.EnvelopRepository;

import lombok.RequiredArgsConstructor;

/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class EnvelopService {

private final EnvelopRepository envelopRepository;
	
	public void save(Envelop envelop) {
		envelopRepository.save(envelop);
	}

}
