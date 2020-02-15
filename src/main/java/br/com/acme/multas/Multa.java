
package br.com.acme.multas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.acme.condominio.Condominio;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tb_multas")
public class Multa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricaoMulta;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataMulta;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condominio_id")
	private Condominio condominioMulta;
	
	private BigDecimal valorMulta;

	public Multa(Long id, String descricaoMulta, LocalDate dataMulta, Condominio condominioMulta,
			BigDecimal valorMulta) {
		super();
		this.id = id;
		this.descricaoMulta = descricaoMulta;
		this.dataMulta = dataMulta;
		this.condominioMulta = condominioMulta;
		this.valorMulta = valorMulta;
	}
	
	public Multa() {
		
	}
	
}
