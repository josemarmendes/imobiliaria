package com.ifma.imobiliaria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Imovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImovel;
	
	@NotBlank
	private String nomeImovel;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String zonaCidade;
	
	@NotNull
	private String cep;	

	@NotNull
	@NumberFormat(pattern = "#,##0.00")
	private String posicaoGeografica;
	
	@NotBlank
	private String pontoDeReferencia;
	
	@NotNull
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal metragem;
	
	@NotNull
	private String dormitorio;
	
	@NotNull(message="Quantidade de banheiros é obrigatório")
	private String banheiro;
	
	@NotNull(message="Quantidade de suítes é obrigatório")
	private String suite;
	
	@NotNull(message="Vagas na Garagem é obrigatório")
	private String vagasGaragem;
	
	@NotNull(message="Valor do Aluguel sugerido é obrigatório")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorAluguelSugerido;
	
	@NotNull(message="Valor do Iptu é obrigatório")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorIptu;
		
	@NotEmpty(message="Memorial descritivo é obrigatório")
	private String memorialDescritivo;

	@NotEmpty(message="Descrição é obrigatória")
	private String requisitosInquilino;
	
	@NotEmpty(message="Observação é obrigatória")
	private String observacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cliente_codigo")
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idTipoImovel")
	private TipoImovel tipoImovel;
	
	public Long getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Long idImovel) {
		this.idImovel = idImovel;
	}
	public String getNomeImovel() {
		return nomeImovel;
	}
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getZonaCidade() {
		return zonaCidade;
	}
	public void setZonaCidade(String zonaCidade) {
		this.zonaCidade = zonaCidade;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getPosicaoGeografica() {
		return posicaoGeografica;
	}
	public void setPosicaoGeografica(String posicaoGeografica) {
		this.posicaoGeografica = posicaoGeografica;
	}
	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}
	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}
	
	public BigDecimal getMetragem() {
		return metragem;
	}
	public void setMetragem(BigDecimal metragem) {
		this.metragem = metragem;
	}
	

	public String getDormitorio() {
		return dormitorio;
	}
	public void setDormitorio(String dormitorio) {
		this.dormitorio = dormitorio;
	}
	public String getBanheiro() {
		return banheiro;
	}
	public void setBanheiro(String banheiro) {
		this.banheiro = banheiro;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getVagasGaragem() {
		return vagasGaragem;
	}
	public void setVagasGaragem(String vagasGaragem) {
		this.vagasGaragem = vagasGaragem;
	}
	public BigDecimal getValorAluguelSugerido() {
		return valorAluguelSugerido;
	}
	public void setValorAluguelSugerido(BigDecimal valorAluguelSugerido) {
		this.valorAluguelSugerido = valorAluguelSugerido;
	}
	public BigDecimal getValorIptu() {
		return valorIptu;
	}
	public void setValorIptu(BigDecimal valorIptu) {
		this.valorIptu = valorIptu;
	}
	
	
	public String getMemorialDescritivo() {
		return memorialDescritivo;
	}
	public void setMemorialDescritivo(String memorialDescritivo) {
		this.memorialDescritivo = memorialDescritivo;
	}
	
	public String getRequisitosInquilino() {
		return requisitosInquilino;
	}
	public void setRequisitosInquilino(String requisitosInquilino) {
		this.requisitosInquilino = requisitosInquilino;
	}
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}
	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idImovel == null) ? 0 : idImovel.hashCode());
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
		Imovel other = (Imovel) obj;
		if (idImovel == null) {
			if (other.idImovel != null)
				return false;
		} else if (!idImovel.equals(other.idImovel))
			return false;
		return true;
	}
	
}
