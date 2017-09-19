package com.ifma.imobiliaria.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class ServicoImovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServicoImovel;
	
	@NotBlank
	private String caminhoMedia;

	@NotNull(message="Valor do Aluguel sugerido é obrigatório")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorTotal;
	
	@NotNull(message="Data de Início do Aluguel é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataServico;
	
	@NotEmpty(message="Observação é obrigatória")
	private String observacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idImovel")
	private Imovel imovel;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idProfissional")
	private Profissional profissional;

	public Long getIdServicoImovel() {
		return idServicoImovel;
	}

	public void setIdServicoImovel(Long idServicoImovel) {
		this.idServicoImovel = idServicoImovel;
	}

	public String getCaminhoMedia() {
		return caminhoMedia;
	}

	public void setCaminhoMedia(String caminhoMedia) {
		this.caminhoMedia = caminhoMedia;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataServico() {
		return dataServico;
	}
	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idServicoImovel == null) ? 0 : idServicoImovel.hashCode());
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
		ServicoImovel other = (ServicoImovel) obj;
		if (idServicoImovel == null) {
			if (other.idServicoImovel != null)
				return false;
		} else if (!idServicoImovel.equals(other.idServicoImovel))
			return false;
		return true;
	}	
}
