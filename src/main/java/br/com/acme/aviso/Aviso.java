
package br.com.acme.aviso;

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


@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tb_avisos")
public class Aviso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricaoAviso;
	
	@ManyToOne
	@JoinColumn(name = "condominio_id")
	private Condominio condominioAviso;

	public Aviso(Long id, String descricaoAviso, Condominio condominioAviso) {
		super();
		this.id = id;
		this.descricaoAviso = descricaoAviso;
		this.condominioAviso = condominioAviso;
	}
	
	public Aviso() {
		
	}
	
}
