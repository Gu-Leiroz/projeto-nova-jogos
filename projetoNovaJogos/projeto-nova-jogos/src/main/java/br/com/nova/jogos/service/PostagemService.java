package br.com.nova.jogos.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
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

	@Autowired
	PagedResourcesAssembler<PostagemVO> assembler;
	
	public PostagemVO findByid(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse ID!"));

		PostagemVO vo = DozerMapper.parseObject(entity, PostagemVO.class);
		vo.add(linkTo(methodOn(PostagemController.class).findById(id)).withSelfRel());
		return vo;
	}

	public PagedModel<EntityModel<PostagemVO>> findAll(Pageable pagable) {
		
		var postPage = repository.findAll(pagable);
		
		var postVOpage = postPage.map(p -> DozerMapper.parseObject(p, PostagemVO.class));
		
		postVOpage.map(p -> p.add(linkTo(methodOn(PostagemController.class).findById(p.getKey())).withSelfRel()));
		
		Link link = linkTo(methodOn(PostagemController.class).findAll(pagable.getPageNumber(), pagable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(postVOpage, link);
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

		entity.setAutor(postagem.getAutor());
		entity.setTitulo(postagem.getTitulo());
		entity.setDescricao(postagem.getDescricao());

		var vo = DozerMapper.parseObject(repository.save(entity), PostagemVO.class);
		vo.add(linkTo(methodOn(PostagemController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}

	public void delete(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse o id " + id));

		repository.delete(entity);
		
	}

}
