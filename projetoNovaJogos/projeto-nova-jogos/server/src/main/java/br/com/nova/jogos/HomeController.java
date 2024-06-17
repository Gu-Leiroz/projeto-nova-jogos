package br.com.nova.jogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nova.jogos.model.Postagem;
import br.com.nova.jogos.service.PostagemService;


@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	PostagemService service;	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public Postagem create(@RequestBody Postagem postagem) {
		return service.create(postagem);
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Postagem> findAll() {
		return service.findAll();
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)	
	public Postagem update(@RequestBody Postagem postagem) {
		
		return service.update(postagem);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
}
