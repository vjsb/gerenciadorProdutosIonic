package com.curso.gerenciadorProdutosSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.gerenciadorProdutosSpring.domain.Cliente;
import com.curso.gerenciadorProdutosSpring.dto.ClienteDTO;
import com.curso.gerenciadorProdutosSpring.repositories.ClienteRepository;
import com.curso.gerenciadorProdutosSpring.resources.exception.DataIntegrityException;

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

	public Cliente update(Cliente obj) throws Exception {
		//esta buscando o cliente do banco
		Cliente newObj = find(obj.getId());
		//atualize os dados de newObj com base no que veio no objeto obj como argumento
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void delete(Integer id) throws Exception {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}

	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);

	}

	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	//atualizar o objeto newObj com os dados que vieram de obj
	//como regra de negocio só ira atualizar o nome e email
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
