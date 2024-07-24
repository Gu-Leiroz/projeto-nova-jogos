package br.com.nova.jogos.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}