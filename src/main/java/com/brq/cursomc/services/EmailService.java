package com.brq.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.domain.PedidoDomain;

public interface EmailService {

	void enviarOrdemConfirmacaoEmail(PedidoDomain pedidoDomain);
	
	void enviarEmail(SimpleMailMessage mailMessage);

	void enviaNovaSenhaEmail(ClienteDomain clienteDomain, String novaSenha);
	
}
