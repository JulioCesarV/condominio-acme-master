package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.repository.UnidadeRepository;
import br.com.acme.unidade.Unidade;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository unidadeRepository;
	
	
	public List<Unidade> listaUnidades(){
		return this.unidadeRepository.findAll();
	}
	
	
	public Unidade save(Unidade unidade) {
		return this.unidadeRepository.save(unidade);
	}
	
	
	public void remove(Long id) {
		this.unidadeRepository.deleteById(id);
	}
	
	
	public Optional <Unidade> getById(Long id) {
		return this.unidadeRepository.findById(id);
	}
	
}
