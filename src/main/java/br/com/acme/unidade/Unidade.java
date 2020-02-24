/**
 * 
 */
package br.com.acme.unidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.condominio.Condominio;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosfilho
 *
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tb_unidade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String numeroUnidade;
	
	private String blocoUnidade;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "condominio_id", nullable = false)
	@JsonIgnore
	private Condominio condominioUnidade;

	
	//@ManyToOne(mappedBy = "unidades")
		//private Set<Condominio> unidadeskey;
	

	public Unidade(Long id, String numeroUnidade, String blocoUnidade) {
		super();
		
		this.numeroUnidade = numeroUnidade;
		this.blocoUnidade = blocoUnidade;
	
	}
	
	public Unidade() {
		
	}
	
	
	
	
	
}
