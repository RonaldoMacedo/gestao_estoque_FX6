package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProduto;
	private String descricaoInterna;
	private LocalDateTime dataCadastro;
	private String grupo;
	private String situacao;
	private Integer saldo;
	
	public Product() {
		
	}

	public Product(Integer idProduto, String descricaoInterna, LocalDateTime dataCadastro, String grupo,
			String situacao, Integer saldo) {
		super();
		this.idProduto = idProduto;
		this.descricaoInterna = descricaoInterna;
		this.dataCadastro = dataCadastro;
		this.grupo = grupo;
		this.situacao = situacao;
		this.saldo = saldo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public String getDescricaoInterna() {
		return descricaoInterna;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getSituacao() {
		return situacao;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public void setDescricaoInterna(String descricaoInterna) {
		this.descricaoInterna = descricaoInterna;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoInterna, idProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(descricaoInterna, other.descricaoInterna) && Objects.equals(idProduto, other.idProduto);
	}

	@Override
	public String toString() {
		return "Product [idProduto=" + idProduto + ", descricaoInterna=" + descricaoInterna + ", dataCadastro="
				+ dataCadastro + ", grupo=" + grupo + ", situacao=" + situacao + ", saldo=" + saldo + "]";
	}

}
