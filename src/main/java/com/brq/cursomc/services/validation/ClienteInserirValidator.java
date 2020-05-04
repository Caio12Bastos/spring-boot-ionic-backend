package com.brq.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brq.cursomc.controllers.exception.CampoMensagem;
import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.dto.NovoClienteDTO;
import com.brq.cursomc.enums.TipoClienteEnum;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.services.validation.utils.CpfCnpjUtilsValidation;

public class ClienteInserirValidator implements ConstraintValidator<ClienteInserirValidation, NovoClienteDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInserirValidation constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(NovoClienteDTO novoClienteDTO, ConstraintValidatorContext context) {

		List<CampoMensagem> listaCampoMensagem = new ArrayList<>();
		
		if(novoClienteDTO.getTipoCliente().equals(TipoClienteEnum.PESSOAFISICA.getCodigo()) && 
				!CpfCnpjUtilsValidation.validaCPF(novoClienteDTO.getCpfCnpj())) {
			
			listaCampoMensagem.add(new CampoMensagem("CpfCnpj", "CPF inválido"));
		}
		
		if(novoClienteDTO.getTipoCliente().equals(TipoClienteEnum.PESSOAJURIDICA.getCodigo()) && 
				!CpfCnpjUtilsValidation.validaCNPJ(novoClienteDTO.getCpfCnpj())) {
			
			listaCampoMensagem.add(new CampoMensagem("CpfCnpj", "CNPJ inválido"));
		}

		ClienteDomain clienteDomain = clienteRepository.findByEmail(novoClienteDTO.getEmail());
		if(clienteDomain != null) {
			listaCampoMensagem.add(new CampoMensagem("email", "Email já existente"));
		}
		
		for(CampoMensagem campoMensagem : listaCampoMensagem) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(campoMensagem.getMensagem())
				.addPropertyNode(campoMensagem.getCampoMensagem()).addConstraintViolation();
		}
		
		return listaCampoMensagem.isEmpty();
	}
	
}
