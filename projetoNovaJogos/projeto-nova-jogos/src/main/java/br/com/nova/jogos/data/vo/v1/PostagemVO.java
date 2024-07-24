package br.com.nova.jogos.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "nome", "valor", "genero", "descricao", "nota"})
public class PostagemVO extends RepresentationModel<PostagemVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;

	private String autor;

	private String descricao;

	private String titulo;

	public PostagemVO() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(autor, descricao, key, titulo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostagemVO other = (PostagemVO) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(key, other.key) && Objects.equals(titulo, other.titulo);
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
