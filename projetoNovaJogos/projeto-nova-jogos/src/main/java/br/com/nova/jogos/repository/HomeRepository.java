package br.com.nova.jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nova.jogos.model.Postagem;

@Repository
public interface HomeRepository extends JpaRepository<Postagem, Long>{

}
