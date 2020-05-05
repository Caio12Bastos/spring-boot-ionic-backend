package com.brq.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brq.cursomc.services.DBService;
import com.brq.cursomc.services.EmailService;
import com.brq.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	private static final Object CREATE = "create";

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String stategy;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		if(!CREATE.equals(stategy)) {
			return false;
		}
		
		dbService.instantiateDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
