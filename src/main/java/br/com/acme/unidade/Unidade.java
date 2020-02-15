/**
 * 
 */
package br.com.acme.unidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "tb_responsavel")
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name = "id_responsavel")
	//private Responsavel responsavelUnidade;
	
	private String numeroUnidade;
	
	private String blocoUnidade;
	
	
	@ManyToOne
	@JoinColumn(name = "condominio_id")
	private Condominio condominioUnidade;

	
	//@ManyToOne(mappedBy = "unidades")
		//private Set<Condominio> unidadeskey;
	

	public Unidade(Long id, String numeroUnidade, String blocoUnidade, Condominio condominioUnidade) {
		super();
		this.id = id;
		this.numeroUnidade = numeroUnidade;
		this.blocoUnidade = blocoUnidade;
		this.condominioUnidade = condominioUnidade;
	}
	
	public Unidade() {
		
	}
	
	
	
	
	
}
