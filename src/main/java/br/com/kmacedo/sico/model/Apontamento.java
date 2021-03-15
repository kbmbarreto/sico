package br.com.kmacedo.sico.model;

import java.math.BigDecimal;
import java.sql.Date;

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
@Table(name = "apontamento")

public class Apontamento {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long idapontamento;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "horas")
	private BigDecimal horas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idservico")
	private Servico servico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idprojeto")
	private Projeto projeto;

	public long getIdapontamento() {
		return idapontamento;
	}

	public void setIdapontamento(long idapontamento) {
		this.idapontamento = idapontamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getHoras() {
		return horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}