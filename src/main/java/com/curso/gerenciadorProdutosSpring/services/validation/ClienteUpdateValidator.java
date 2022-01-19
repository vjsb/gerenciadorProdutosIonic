package com.curso.gerenciadorProdutosSpring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.curso.gerenciadorProdutosSpring.domain.Cliente;
import com.curso.gerenciadorProdutosSpring.dto.ClienteDTO;
import com.curso.gerenciadorProdutosSpring.repositories.ClienteRepository;
import com.curso.gerenciadorProdutosSpring.resources.exception.FieldMessage;

//classe ConstraintValidator é a importação responsavel por validar
//mudamos aqui de ClienteNewDTO para ClienteDTO porque é o DTO especifico que usamos para atualizar
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	//permite obter o parametro da URI que é passda no postman
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	ClienteRepository repo;
	
	public void initialize(ClienteUpdate ann) {
		
	}
	
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		//método que pega o id passado na URI do postman
		//pega o mapping de variaveis de URI que estão na requisição
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		//inclua os testes aqui, inserindo erros nas lista
		//verificação de email, ver se ele é nulo e não deixar atualizar um email com um ja existente de outro cliente
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		//método próprio do framework que faz a inserção do erro na lista, e vai adicionando caso ocorra erros
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
		
	}
}
