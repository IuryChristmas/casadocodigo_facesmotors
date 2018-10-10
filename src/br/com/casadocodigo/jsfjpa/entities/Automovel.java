package br.com.casadocodigo.jsfjpa.entities;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import br.com.casadocodigo.jsfjpa.validation.MaxAnoAtualMais;
import br.com.casadocodigo.jsfjpa.validation.groups.ValidacaoMinima;

@NamedQueries({
	@NamedQuery(name=Automovel.LISTAR_DESTAQUES, query="select a from Automovel a", hints={
			@QueryHint(name="org.hibernate.cacheable", value="true"),
			@QueryHint(name="org.hibernate.cacheRegion", value=Automovel.LISTAR_DESTAQUES)})
})
@Entity
public class Automovel {
	
	public static final String LISTAR_DESTAQUES = "Automovel.buscarDestaques";
	
	@Id @GeneratedValue
	private Long id;
	private String marca;
	
	@ManyToOne
	private Modelo modelo;
	@Min(1900)
	@MaxAnoAtualMais(message="O máximo do ano de fabricação é {0}")
	private Integer anoFabricacao;
	@Min(1900)
	@MaxAnoAtualMais(message="O máximo do ano do modelo é {0}")
	private Integer anoModelo;
	private String observacoes;
	@NotNull(groups= {ValidacaoMinima.class, Default.class})
	private Float preco;
	private Float kilometragem;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public Integer getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public Float getPreco() {
		return preco;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	public Float getKilometragem() {
		return kilometragem;
	}
	
	public void setKilometragem(Float kilometragem) {
		this.kilometragem = kilometragem;
	}
	
}
