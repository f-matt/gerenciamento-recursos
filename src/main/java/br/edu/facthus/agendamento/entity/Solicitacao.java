package br.edu.facthus.agendamento.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitacoes")
public class Solicitacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "dthr_solicitacao")
	private LocalDateTime dthrSolicitacao;
	
	@Column(name = "dthr_devolucao")
	private LocalDateTime dthrDevolucao;
	
	@ManyToOne
	@JoinColumn(name = "recurso_id")
	private Recurso recurso;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusSolicitacao status;
	
	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "Solicitacao [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Solicitacao other = (Solicitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDthrSolicitacao() {
		return dthrSolicitacao;
	}

	public void setDthrSolicitacao(LocalDateTime dthrSolicitacao) {
		this.dthrSolicitacao = dthrSolicitacao;
	}

	public LocalDateTime getDthrDevolucao() {
		return dthrDevolucao;
	}

	public void setDthrDevolucao(LocalDateTime dthrDevolucao) {
		this.dthrDevolucao = dthrDevolucao;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public StatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacao status) {
		this.status = status;
	}
	
}
