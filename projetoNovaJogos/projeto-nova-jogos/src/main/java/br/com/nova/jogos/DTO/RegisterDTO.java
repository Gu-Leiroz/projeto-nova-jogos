package br.com.nova.jogos.DTO;

import java.util.List;

import br.com.nova.jogos.model.Permission;

public record RegisterDTO(String Username, String email, String password, List<Permission> Permission) {

}
