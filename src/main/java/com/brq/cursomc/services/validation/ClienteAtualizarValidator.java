package com.brq.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.brq.cursomc.controllers.exception.CampoMensagem;
import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.dto.ClienteDTO;
import com.brq.cursomc.repositories.ClienteRepository;

public class ClienteAtualizarValidator implements ConstraintValidator<ClienteAtualizarValidation, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteAtualizarValidation constraintAnnotation) {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {

		Map<String, String> mapURI = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer idURI = Integer.parseInt(mapURI.get("id"));
		
		List<CampoMensagem> listaCampoMensagem = new ArrayList<>();
		
		ClienteDomain clienteDomain = clienteRepository.findByEmail(clienteDTO.getEmail());
		if(clienteDomain != null && !clienteDomain.getId().equals(idURI)) {
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
