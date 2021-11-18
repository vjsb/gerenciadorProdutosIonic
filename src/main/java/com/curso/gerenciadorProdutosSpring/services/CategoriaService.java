package com.curso.gerenciadorProdutosSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.curso.gerenciadorProdutosSpring.domain.Categoria;
import com.curso.gerenciadorProdutosSpring.repositories.CategoriaRepository;
import com.curso.gerenciadorProdutosSpring.resources.exception.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) throws Exception {
		
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
	
	//save cria caso o obj não tenha id
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	//save quando ve que o obj possui id ele o atualiza
	public Categoria update(Categoria obj) throws Exception {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
	
	public  void delete(Integer id) throws Exception {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
}
