package br.com.nova.jogos.securityJwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.nova.jogos.data.vo.v1.security.TokenVO;
import br.com.nova.jogos.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-length:3600000}")
	private Long validInMilliseconds = 3600000L;

	@Autowired
	private UserDetailsService userDetailsService;

	Algorithm algorithm = null;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}

	public TokenVO createTokenAcesso(String username, List<String> roles) {

		Date now = new Date();
		Date validacao = new Date(now.getTime() + validInMilliseconds);
		var accessToken = getAccesToken(username, roles, now, validacao);
		var refreshToken = getRefreshToken(username, roles, now);
		return new TokenVO(username, true, now, validacao, accessToken, refreshToken);

	}

	public TokenVO createRefreshToken(String refreshToken) {
		
		if(refreshToken.contains("Bearer ")) 
			refreshToken = refreshToken.substring("Bearer ".length());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(refreshToken);
		String username = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		return createTokenAcesso(username, roles);
	}

	private String getAccesToken(String username, List<String> roles, Date now, Date validacao) {
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

		return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validacao).withSubject(username)
				.withIssuer(issuerUrl).sign(algorithm).strip();
	}

	private String getRefreshToken(String username, List<String> roles, Date now) {

		Date validacaoRefresh = new Date(now.getTime() + (validInMilliseconds * 3));

		return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validacaoRefresh)
				.withSubject(username).sign(algorithm).strip();
	}

	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private DecodedJWT decodedToken(String token) {

		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decodedJWT = verifier.verify(token);

		return decodedJWT;
	}

	public String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");

		// Bearer
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJndXN0YXZvIiwicm9sZXMiOlsiQURNSU4iLCJNQU5BR0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCIsImV4cCI6MTUxNjIzOTAyMiwiaWF0IjoxNTE2MjM5MDIyfQ.2Zj7kJJQnWY8W3WiDV3xGoXGTR_hKLjb5UBS0Ec8UZE
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}

	public boolean validateToken(String token) {

		DecodedJWT decodedJWT = decodedToken(token);

		try {
			if (decodedJWT.getExpiresAt().before(new Date())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException("Token expirado ou inválido!");
		}
	}
}
