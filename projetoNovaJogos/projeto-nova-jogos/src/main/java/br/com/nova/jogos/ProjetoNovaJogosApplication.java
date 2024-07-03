package br.com.nova.jogos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

@SpringBootApplication
public class ProjetoNovaJogosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoNovaJogosApplication.class, args);

		Map<String, PasswordEncoder> enconders = new HashMap<>();

		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("", 8, 185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

		enconders.put("pbkdf2", pbkdf2PasswordEncoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", enconders);

		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);

		String result = passwordEncoder.encode("12102002");
		System.out.println("My Hash " + result);
	
	}

}
