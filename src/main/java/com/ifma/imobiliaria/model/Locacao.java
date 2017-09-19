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
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLocacao;
	
	@NotBlank
	private String periodicidade;
	
	@NotBlank
	private String responsavelPagamento;
	
	@NotBlank
	private String caminhoSituacaoImovel;
	
	@NotNull(message="Valor do Aluguel sugerido é obrigatório")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorAluguelLocacao;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal percentualMulta;
		
	@NotEmpty(message="Observação é obrigatória")
	private String observacao;
	
	@NotNull(message="Dia do vencimento é obrigatório")
	private Integer diaDoVencimento;
	
	
	@NotNull(message="Data de Início do Aluguel é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@NotNull(message="Data Fim do Aluguel é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cliente_codigo")
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idImovel")
	private Imovel imovel;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idProfissional")
	private Profissional profissional;

	public Long getIdLocacao() {
		return idLocacao;
	}
	public void setIdLocacao(Long idLocacao) {
		this.idLocacao = idLocacao;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}
	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	public String getResponsavelPagamento() {
		return responsavelPagamento;
	}
	public void setResponsavelPagamento(String responsavelPagamento) {
		this.responsavelPagamento = responsavelPagamento;
	}

	public String getCaminhoSituacaoImovel() {
		return caminhoSituacaoImovel;
	}
	public void setCaminhoSituacaoImovel(String caminhoSituacaoImovel) {
		this.caminhoSituacaoImovel = caminhoSituacaoImovel;
	}

	public BigDecimal getValorAluguelLocacao() {
		return valorAluguelLocacao;
	}
	public void setValorAluguelLocacao(BigDecimal valorAluguelLocacao) {
		this.valorAluguelLocacao = valorAluguelLocacao;
	}

	public BigDecimal getPercentualMulta() {
		return percentualMulta;
	}
	public void setPercentualMulta(BigDecimal percentualMulta) {
		this.percentualMulta = percentualMulta;
	}

	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getDiaDoVencimento() {
		return diaDoVencimento;
	}
	public void setDiaDoVencimento(Integer diaDoVencimento) {
		this.diaDoVencimento = diaDoVencimento;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		result = prime * result + ((idLocacao == null) ? 0 : idLocacao.hashCode());
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
		Locacao other = (Locacao) obj;
		if (idLocacao == null) {
			if (other.idLocacao != null)
				return false;
		} else if (!idLocacao.equals(other.idLocacao))
			return false;
		return true;
	}
		
}
