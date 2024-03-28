package com.soloproject1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.soloproject1.interceptor.PermissionInterceptor;

@Configuration
@PropertySource("classpath:application.yml")
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private PermissionInterceptor interceptor;

	@Autowired
	private Environment env;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("https://solo-project-1-c8107.web.app", "https://goodorbad.site").allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/static/**", "/error");
	}

	@Bean
	public WebClient webClient() {
		String baseUrl = env.getProperty("tmdb.baseUrl");
		String authToken = env.getProperty("tmdb.authToken");
		return WebClient.builder().baseUrl(baseUrl).defaultHeader("Authorization", authToken).build();
	}

}
