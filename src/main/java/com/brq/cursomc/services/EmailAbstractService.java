package com.brq.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.brq.cursomc.domain.ClienteDomain;
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
	
	@Override
	public void enviaNovaSenhaEmail(ClienteDomain clienteDomain, String novaSenha) {
		
		SimpleMailMessage mailMessage = preparaEmailNovaSenha(clienteDomain, novaSenha);
		enviarEmail(mailMessage);
	}

	private SimpleMailMessage preparaEmailNovaSenha(ClienteDomain clienteDomain, String novaSenha) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(clienteDomain.getEmail());
		mailMessage.setFrom(remetente);
		mailMessage.setSubject("Solicitação de nova senha.");
		mailMessage.setSentDate(new Date(System.currentTimeMillis()));
		mailMessage.setText("Nova senha: " + novaSenha);
		
		return mailMessage;
	}
	
}
