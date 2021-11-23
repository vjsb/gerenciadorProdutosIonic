package com.curso.gerenciadorProdutosSpring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.gerenciadorProdutosSpring.domain.Categoria;
import com.curso.gerenciadorProdutosSpring.dto.CategoriaDTO;
import com.curso.gerenciadorProdutosSpring.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws Exception {
		Categoria obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	
	//requestBody faz o JSON ser convertido para objeto Java automaticamente
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	//recebe o PathVariable e o RequestBody por ter que pegar um objeto e atualiza-lo
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) throws Exception{
		obj.setId(id);
		obj = service.update(obj);
		//quando se atualiza com sucesso retorna um 204(NoContent)
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//retorna todas as categorias, no caso DTO porque só queremos o id e o nome dels e não seus produtos
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() throws Exception {
		List<Categoria> list = service.findAll();
		//com essa lista convertemos uma lista de Categoria para CategoriaDTO
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
	
	
}
