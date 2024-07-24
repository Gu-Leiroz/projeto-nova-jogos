package br.com.nova.jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nova.jogos.data.vo.v1.security.AccountCredentialsVO;
import br.com.nova.jogos.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "autenticação de usuarios")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService service;

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
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if (verificaParametros(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Parâmetros do cliente invalido!");
		
		var token = service.signin(data);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Parâmetros do cliente invalido!");
		
		return token;
	}

	private boolean verificaParametros(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
				|| data.getPassword().isBlank();
	}
}
