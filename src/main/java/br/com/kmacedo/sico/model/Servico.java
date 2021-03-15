package br.com.kmacedo.sico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servico")

public class Servico {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long idservico;
	
	@Column(name = "descricao")
	private String descricao;

	public long getIdservico() {
		return idservico;
	}

	public void setIdservico(long idservico) {
		this.idservico = idservico;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}