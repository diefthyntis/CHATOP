package com.diefthyntis.chatop.diefthyntis.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Value("${diefthyntis.store}")
	private String storePlace;
	
	@Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		final Path uploadDir = Paths.get(storePlace);
        final String uploadPath = uploadDir.toFile().getAbsolutePath();
        final String pathPattern = "/api/images/**";
        final String resourceLocation = "file:/" + uploadPath + "/";
        registry.addResourceHandler(pathPattern).addResourceLocations(resourceLocation);
    }
	/*
	 * pour tester la gestion des images, http://localhost:3001/api/images/1/20240816035133.jpg
	 */
}
