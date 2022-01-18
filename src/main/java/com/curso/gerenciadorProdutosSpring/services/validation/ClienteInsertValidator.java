package com.curso.gerenciadorProdutosSpring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.curso.gerenciadorProdutosSpring.domain.enums.TipoCliente;
import com.curso.gerenciadorProdutosSpring.dto.ClienteNewDTO;
import com.curso.gerenciadorProdutosSpring.resources.exception.FieldMessage;
import com.curso.gerenciadorProdutosSpring.services.validation.utils.BR;

//classe ConstraintValidator é a importação responsavel por validar
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	public void initialize(ClienteInsert ann) {
		
	}
	
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		//inclua os testes aqui, inserindo erros nas lista
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
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
