package br.com.nova.jogos.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nova.jogos.controller.UsersController;
import br.com.nova.jogos.data.vo.v1.UserVO;
import br.com.nova.jogos.exceptions.ResourceNotFoundException;
import br.com.nova.jogos.mapper.DozerMapper;
import br.com.nova.jogos.model.User;
import br.com.nova.jogos.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private Logger logger = Logger.getLogger(UserService.class.getName());

	@Autowired
	UserRepository repository;

	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("Achando um Usuario pelo Nome");

		var user = repository.findByUsername(username);

		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
	}

	public UserVO findByid(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse ID!"));

		UserVO vo = DozerMapper.parseObject(entity, UserVO.class);
		vo.add(linkTo(methodOn(UsersController.class).findById(id)).withSelfRel());
		return vo;
	}

	public List<UserVO> findAll() {
		
		var posts = DozerMapper.parseListObjects(repository.findAll(), UserVO.class);
			posts.stream()
					.forEach(p -> p.add(linkTo(methodOn(UsersController.class).findById(p.getKey())).withSelfRel()));

			return posts;
		}

	public UserVO create(UserVO user) {

		var entity = DozerMapper.parseObject(user, User.class);
		var vo = DozerMapper.parseObject(repository.save(entity), UserVO.class);
		vo.add(linkTo(methodOn(UsersController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}

	public UserVO update(UserVO user) {

		var entity = repository.findById(user.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse ID!"));

		entity.setUsername(user.getUsername());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());

		var vo = DozerMapper.parseObject(repository.save(entity), UserVO.class);
		vo.add(linkTo(methodOn(UsersController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}

	public void delete(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma postagem encontrada com esse o id " + id));

		repository.delete(entity);
		
	}
}