package com.brq.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.brq.cursomc.controllers.exception.CampoMensagem;
import com.brq.cursomc.dto.NovoClienteDTO;
import com.brq.cursomc.enums.TipoClienteEnum;
import com.brq.cursomc.services.validation.utils.CpfCnpjUtils;

public class ClienteValidator implements ConstraintValidator<ClienteValidation, NovoClienteDTO> {

	@Override
	public void initialize(ClienteValidation constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(NovoClienteDTO novoClienteDTO, ConstraintValidatorContext context) {

		List<CampoMensagem> listaCampoMensagem = new ArrayList<>();
		
		if(novoClienteDTO.getTipoCliente().equals(TipoClienteEnum.PESSOAFISICA.getCodigo()) && 
				!CpfCnpjUtils.validaCPF(novoClienteDTO.getCpfCnpj())) {
			
			listaCampoMensagem.add(new CampoMensagem("CpfCnpj", "CPF inválido"));
		}
		
		if(novoClienteDTO.getTipoCliente().equals(TipoClienteEnum.PESSOAJURIDICA.getCodigo()) && 
				!CpfCnpjUtils.validaCNPJ(novoClienteDTO.getCpfCnpj())) {
			
			listaCampoMensagem.add(new CampoMensagem("CpfCnpj", "CNPJ inválido"));
		}
		
		for(CampoMensagem campoMensagem : listaCampoMensagem) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(campoMensagem.getMensagem())
				.addPropertyNode(campoMensagem.getCampoMensagem()).addConstraintViolation();
		}
		
		return listaCampoMensagem.isEmpty();
	}
	
		
	
}
