package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.multas.Multa;
import br.com.acme.repository.MultaRepository;

@Service
public class MultaService {

	@Autowired
	private MultaRepository multaRepository;
	
	
	public List<Multa> listaMultas(){
		return this.multaRepository.findAll();
	}
	
	
	public Multa save(Multa multa) {
		return this.multaRepository.save(multa);
	}
	
	
	public void remove(Long id) {
		this.multaRepository.deleteById(id);
	}
	
	
	public Optional <Multa> getById(Long id) {
		return this.multaRepository.findById(id);
	}
	
}
