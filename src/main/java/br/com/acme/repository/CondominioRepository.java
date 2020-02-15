package br.com.acme.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.condominio.Condominio;


public interface CondominioRepository extends JpaRepository<Condominio, Long> {


	
}
