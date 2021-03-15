package br.com.kmacedo.sico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")

public class Cliente {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long idcliente;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idplano")
	private Plano plano;
	
	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}