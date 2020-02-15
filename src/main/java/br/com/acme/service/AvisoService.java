package br.com.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.aviso.Aviso;
import br.com.acme.repository.AvisoRepository;

@Service
public class AvisoService {

	@Autowired
	private AvisoRepository avisorepository;
	

	public List<Aviso> listaAvisos(){
		return this.avisorepository.findAll();
	}
	
	
	public Aviso save(Aviso aviso) {
		return this.avisorepository.save(aviso);
	}
	
	
	public void remove(Long id) {
		this.avisorepository.deleteById(id);
	}
	
	
	public Optional <Aviso> getById(Long id) {
		return this.avisorepository.findById(id);
	}
	
}
