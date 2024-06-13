package com.soloproject1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@PropertySource("classpath:application.yml")
public class WebClientConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public WebClient tmdbWebClient() {
		String baseUrl = env.getProperty("tmdb.baseUrl");
		String authToken = env.getProperty("tmdb.authToken");
		return WebClient.builder().baseUrl(baseUrl).defaultHeader("Authorization", authToken).build();
	}
	
}
