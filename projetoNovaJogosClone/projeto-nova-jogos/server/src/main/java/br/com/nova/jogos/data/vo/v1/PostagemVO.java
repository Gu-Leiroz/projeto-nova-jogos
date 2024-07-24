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

	private String nome;

	private Double valor;

	private String genero;

	private String descricao;

	private Double nota;

	public PostagemVO() {
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descricao, genero, key, nome, nota, valor);
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
		return Objects.equals(descricao, other.descricao) && Objects.equals(genero, other.genero)
				&& Objects.equals(key, other.key) && Objects.equals(nome, other.nome)
				&& Objects.equals(nota, other.nota) && Objects.equals(valor, other.valor);
	}
}
