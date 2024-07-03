package br.com.nova.jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nova.jogos.data.vo.v1.security.AccountCredentialsVO;
import br.com.nova.jogos.data.vo.v1.security.TokenVO;
import br.com.nova.jogos.repository.UserRepository;
import br.com.nova.jogos.securityJwt.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private JwtTokenProvider provider;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserRepository repository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = repository.findByUsername(username);
			var tokenResp = new TokenVO();
			if (user != null) {
				tokenResp = provider.createTokenAcesso(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + "não encontrado!");
			}
			return ResponseEntity.ok(tokenResp);
		} catch (Exception e) {
			throw new BadCredentialsException("Username/Senha Inválidos!");
		}

	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity refresh(String username, String refeshToken) {
		var user = repository.findByUsername(username);
		var tokenResp = new TokenVO();
		if (user != null) {
			tokenResp = provider.createRefreshToken(refeshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + "não encontrado!");
		}
		return ResponseEntity.ok(tokenResp);

	}
}
