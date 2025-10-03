package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUnidade;
	private String nomeUnidade;
	private String cidade;
	private String situacao;
	
	public Unit() {
		
	}

	public Unit(Integer idUnidade, String nomeUnidade, String cidade, String situacao) {
		super();
		this.idUnidade = idUnidade;
		this.nomeUnidade = nomeUnidade;
		this.cidade = cidade;
		this.situacao = situacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public String getCidade() {
		return cidade;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUnidade, nomeUnidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		return Objects.equals(idUnidade, other.idUnidade) && Objects.equals(nomeUnidade, other.nomeUnidade);
	}

	@Override
	public String toString() {
		return "Unit [idUnidade=" + idUnidade + ", nomeUnidade=" + nomeUnidade + ", cidade=" + cidade + ", situacao="
				+ situacao + "]";
	}

}
