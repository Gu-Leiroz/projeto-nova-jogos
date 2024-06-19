package br.com.nova.jogos.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nova.jogos.exceptions.ResourceNotFoundException;
import br.com.nova.jogos.model.Postagem;
import br.com.nova.jogos.repository.HomeRepository;

@Service
public class PostagemService {

	
	private Logger logger = Logger.getLogger(PostagemService.class.getName());
	
	@Autowired
	HomeRepository repository;
	
	public List<Postagem> findAll() {
		
		return repository.findAll();
	}
	
	public Postagem create(Postagem postagem) {
		
		logger.info("chamando");
		
		
		return repository.save(postagem);
	}

	
	public Postagem update(Postagem postagem) {
		
		return repository.save(postagem);
	}
	
	public void delete(Long id) {
		
		logger.info("Deletando Pessoa");
		
		var entity = repository.findById(id).orElseThrow(() 
		-> new ResourceNotFoundException("No record found for this ID :"));
					
		repository.delete(entity);
	}
	
}
