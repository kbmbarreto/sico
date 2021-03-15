package br.com.kmacedo.sico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plano")
public class Plano {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long idplano;
	
	@Column(name = "descricao")
	private String descricao;

	public long getIdplano() {
		return idplano;
	}

	public void setIdplano(long idplano) {
		this.idplano = idplano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
