package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Brand implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idMarca;
	private String nomeFantasia;
	
	public Brand() {
		
	}

	public Brand(Integer idMarca, String nomeFantasia) {
		super();
		this.idMarca = idMarca;
		this.nomeFantasia = nomeFantasia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMarca, nomeFantasia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		return Objects.equals(idMarca, other.idMarca) && Objects.equals(nomeFantasia, other.nomeFantasia);
	}

	@Override
	public String toString() {
		return "Brand [idMarca=" + idMarca + ", nomeFantasia=" + nomeFantasia + "]";
	}

}
