package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
	private String jwtSecret;
	private String jwtExpirationInMs;
	private String title;
	public String getJwtSecret() {
		return jwtSecret;
	}
	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
	public String getJwtExpirationInMs() {
		return jwtExpirationInMs;
	}
	public void setJwtExpirationInMs(String jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
