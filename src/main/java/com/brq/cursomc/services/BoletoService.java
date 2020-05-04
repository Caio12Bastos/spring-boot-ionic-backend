package com.brq.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.PagamentoBoletoDomain;

@Service
public class BoletoService {

	public void preencherPagamentoBoleto(PagamentoBoletoDomain pagamentoBoletoDomain, Date dataPedido) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoBoletoDomain.setDataVencimento(calendar.getTime());
	}
}
