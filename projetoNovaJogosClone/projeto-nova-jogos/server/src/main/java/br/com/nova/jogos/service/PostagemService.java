package br.com.nova.jogos.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nova.jogos.controller.PostagemController;
import br.com.nova.jogos.data.vo.v1.PostagemVO;
import br.com.nova.jogos.exceptions.ResourceNotFoundException;
import br.com.nova.jogos.mapper.DozerMapper;
import br.com.nova.jogos.model.Postagem;
import br.com.nova.jogos.repository.HomeRepository;

@Service
public class PostagemService {


	@Autowired
	HomeRepository repository;

	public PostagemVO findByid(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse ID!"));

		PostagemVO vo = DozerMapper.parseObject(entity, PostagemVO.class);
		vo.add(linkTo(methodOn(PostagemController.class).findById(id)).withSelfRel());
		return vo;
	}

	public List<PostagemVO> findAll() {
		var posts = DozerMapper.parseListObjects(repository.findAll(), PostagemVO.class);
		posts.stream()
				.forEach(p -> p.add(linkTo(methodOn(PostagemController.class).findById(p.getKey())).withSelfRel()));

		return posts;
	}

	public PostagemVO create(PostagemVO postagem) {

		var entity = DozerMapper.parseObject(postagem, Postagem.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PostagemVO.class);
		vo.add(linkTo(methodOn(PostagemController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}

	public PostagemVO update(PostagemVO postagem) {

		var entity = repository.findById(postagem.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse ID!"));

		entity.setNome(postagem.getNome());
		entity.setValor(postagem.getValor());
		entity.setGenero(postagem.getGenero());
		entity.setDescricao(postagem.getDescricao());
		entity.setNota(postagem.getNota());

		var vo = DozerMapper.parseObject(entity, PostagemVO.class);
		vo.add(linkTo(methodOn(PostagemController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}

	public void delete(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse o id " + id));

		repository.delete(entity);
		
	}

}
