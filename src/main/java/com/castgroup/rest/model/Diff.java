package com.castgroup.rest.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.castgroup.rest.enuns.TipoDiff;

@Entity
@Table(name = "TB_DIFF")
public class Diff {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long codigoHash;
	@NotEmpty(message="Por favor, digite um codigo BASE64 válido!")
	private String hash;
	@Enumerated(EnumType.STRING)
	private TipoDiff tipoDiff;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCodigoHash() {
		return codigoHash;
	}
	public void setCodigoHash(long codigoHash) {
		this.codigoHash = codigoHash;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public TipoDiff getTipoDiff() {
		return tipoDiff;
	}
	public void setTipoDiff(TipoDiff tipoDiff) {
		this.tipoDiff = tipoDiff;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diff other = (Diff) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
}
