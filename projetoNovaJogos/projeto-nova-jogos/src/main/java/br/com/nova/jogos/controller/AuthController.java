package br.com.nova.jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nova.jogos.DTO.RegisterDTO;
import br.com.nova.jogos.data.vo.v1.security.AccountCredentialsVO;
import br.com.nova.jogos.model.User;
import br.com.nova.jogos.repository.UserRepository;
import br.com.nova.jogos.service.AuthService;
import br.com.nova.jogos.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "autenticação de usuarios")
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService service;
	
	@Autowired
	UserRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Atualização de token para autenticar e devolver o token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refresh(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		if (refreshToken == null || refreshToken.isBlank() 
				|| username == null || username.isBlank())
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Parâmetros do cliente invalido!");
		var token = service.refresh(username, refreshToken);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Parâmetros do cliente invalido!");

		return token;
	}
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Autentica usuario e retorna token")
	@PostMapping(value = "/signin", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
			 consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if (verificaParametros(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Parâmetros do cliente invalido!");
		
		var token = service.signin(data);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Parâmetros do cliente invalido!");
		
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterDTO data) {
		if(this.repository.findByUsername(data.Username()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new Pbkdf2PasswordEncoder("", 8, 185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256).encode(data.password());
		
		User newUser = new User(data.Username(), data.email() ,encryptedPassword, data.Permission());
		
		this.repository.save(newUser);
		
		return ResponseEntity.ok().build();
			
	}
	

	private boolean verificaParametros(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
				|| data.getPassword().isBlank();
	}
	
	
	
	
}
