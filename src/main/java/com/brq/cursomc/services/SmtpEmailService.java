package com.brq.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends EmailAbstractService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public void enviarEmail(SimpleMailMessage mailMessage) {

		LOGGER.info("Enviando email...");
		mailSender.send(mailMessage);
		LOGGER.info("Email enviado.");
		
	}

}
