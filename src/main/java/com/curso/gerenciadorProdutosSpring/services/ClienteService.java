package com.curso.gerenciadorProdutosSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.gerenciadorProdutosSpring.domain.Cliente;
import com.curso.gerenciadorProdutosSpring.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente find(Integer id) throws Exception {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
}
