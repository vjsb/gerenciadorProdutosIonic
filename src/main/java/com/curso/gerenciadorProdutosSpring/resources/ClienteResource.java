package com.curso.gerenciadorProdutosSpring.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.gerenciadorProdutosSpring.domain.Cliente;
import com.curso.gerenciadorProdutosSpring.domain.enums.TipoCliente;
import com.curso.gerenciadorProdutosSpring.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws Exception {
		Cliente obj = service.find(id);
		
		Cliente cli1 =  new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		List<Cliente> listaClientes = new ArrayList<>();
		listaClientes.add(cli1);
		
		return ResponseEntity.ok(obj);
	}

}
