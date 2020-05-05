package com.brq.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.brq.cursomc.domain.PedidoDomain;

public abstract class EmailAbstractService implements EmailService {

	@Value("${default.remetente}")
	private String remetente;
	@Override
	public void enviarOrdemConfirmacaoEmail(PedidoDomain pedidoDomain) {
		
		SimpleMailMessage mailMessage = preparaEmailMensagemPedido(pedidoDomain);
		enviarEmail(mailMessage);
	}

	protected SimpleMailMessage preparaEmailMensagemPedido(PedidoDomain pedidoDomain) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(pedidoDomain.getCliente().getEmail());
		mailMessage.setFrom(remetente);
		mailMessage.setSubject("Pedido confimardo: " + pedidoDomain.getId());
		mailMessage.setSentDate(new Date(System.currentTimeMillis()));
		mailMessage.setText(pedidoDomain.toString());
		
		return mailMessage;
	}
	
}
