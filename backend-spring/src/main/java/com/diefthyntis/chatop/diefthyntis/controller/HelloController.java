package com.diefthyntis.chatop.diefthyntis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
	@GetMapping("/titi")
    public String sayhelloworld() {
		// ? signifie la généricité, donc je peux passer n'importe quel type d'objet dans la méthode responseEntity.ok
		return "hello world";
	}
}
