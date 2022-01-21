package com.curso.gerenciadorProdutosSpring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.gerenciadorProdutosSpring.domain.Produto;
import com.curso.gerenciadorProdutosSpring.dto.ProdutoDTO;
import com.curso.gerenciadorProdutosSpring.resources.utils.URL;
import com.curso.gerenciadorProdutosSpring.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) throws Exception {
		Produto obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	// endpoint do método search criado no repository
	// retorna uma pagina contendo os dados
	@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<Page<ProdutoDTO>> findPage(
				//criamos o parametro nome e categorias para conseguirmos passar eles na URI do postman
				@RequestParam(value = "nome", defaultValue = "") String nome, 
				@RequestParam(value = "categorias", defaultValue = "") String categorias, 
				@RequestParam(value = "page", defaultValue = "0") Integer page, 
				@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
				@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
				@RequestParam(value = "direction", defaultValue = "ASC") String direction) throws Exception {
			//para se fazer essa conversão dos tipos do método search criamos uma classe no utils de resource, nome de URL
			String nomeDecoded = URL.decodeParam(nome);
			List<Integer> ids = URL.decodeIntList(categorias);
			Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
			Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
			return ResponseEntity.ok(listDto);
	}
	
}
