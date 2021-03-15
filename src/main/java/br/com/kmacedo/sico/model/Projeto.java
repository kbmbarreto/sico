package br.com.kmacedo.sico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projeto")
public class Projeto {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long idprojeto;
	
	@Column(name = "descricao")
	private String descricao;

	public long getIdprojeto() {
		return idprojeto;
	}

	public void setIdprojeto(long idprojeto) {
		this.idprojeto = idprojeto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
