package com.diefthyntis.chatop.diefthyntis.service;

import org.springframework.stereotype.Service;

import com.diefthyntis.chatop.diefthyntis.io.fronttoback.EnvelopRequest;
import com.diefthyntis.chatop.diefthyntis.mapping.EnvelopMapping;
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
	private final EnvelopMapping envelopMapping;

	public void save(EnvelopRequest envelopRequest) {
		final Envelop envelop = envelopMapping.mapEnvelopRequestToEnvelop(envelopRequest);
		
		envelopRepository.save(envelop);
	}

}
