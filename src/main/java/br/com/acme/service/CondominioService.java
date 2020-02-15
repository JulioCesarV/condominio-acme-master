package br.com.acme.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.condominio.Condominio;
import br.com.acme.repository.CondominioRepository;

@Service
public class CondominioService {

	@Autowired
	private CondominioRepository condominioRepository;
	
	
	public List<Condominio> listaCondominios(){
		return this.condominioRepository.findAll();
		
	}
	
	public Condominio save(Condominio condominio) {
		return this.condominioRepository.save(condominio);
	}
	

	public void remove(Long id) {
		this.condominioRepository.deleteById(id);
	}
	

	public Optional<Condominio> getById(Long id) {
		return this.condominioRepository.findById(id);
	}
	
	
	//public List<Condominio> filtrar(ContatoPesquisaDTO contato) {
	//	String nome = contato.getNome() == null ? "%" : contato.getNome()+"%";
		//return repository.findByNomeContaining(nome);
	//}


	
	
}

	
