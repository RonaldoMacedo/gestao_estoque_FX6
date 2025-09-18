package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idFornecedor;
	private String razaoSocial;
	private String apelido;
	private String cnpj;
	private LocalDateTime dataCadastro;
	private String situacao;
	
	public Supplier() {
		
	}

	public Supplier(Integer idFornecedor, String razaoSocial, String apelido, String cnpj, LocalDateTime dataCadastro,
			String situacao) {
		super();
		this.idFornecedor = idFornecedor;
		this.razaoSocial = razaoSocial;
		this.apelido = apelido;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.situacao = situacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getApelido() {
		return apelido;
	}

	public String getCnpj() {
		return cnpj;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apelido, idFornecedor, razaoSocial);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return Objects.equals(apelido, other.apelido) && Objects.equals(idFornecedor, other.idFornecedor)
				&& Objects.equals(razaoSocial, other.razaoSocial);
	}

	@Override
	public String toString() {
		return "Supplier [idFornecedor=" + idFornecedor + ", razaoSocial=" + razaoSocial + ", apelido=" + apelido
				+ ", cnpj=" + cnpj + ", dataCadastro=" + dataCadastro + ", situacao=" + situacao + "]";
	}

}
