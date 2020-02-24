package br.com.acme.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.aviso.Aviso;
import br.com.acme.exception.NotFoundException;
import br.com.acme.service.AvisoService;
import br.com.acme.service.CondominioService;

@RestController
@RequestMapping("/api")
public class AvisoController {

	@Autowired
	private AvisoService avisoService;
	
	@Autowired
	private CondominioService condominioService;


	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/condominios/{id_condominio}/avisos")
	public ResponseEntity<Aviso> cadastrar(@PathVariable ("id_condominio") Long id, @Valid @RequestBody Aviso aviso) {
		return this.condominioService.getById(id).map(condominio -> {
            aviso.setCondominioAviso(condominio);
            avisoService.save(aviso);
            return ResponseEntity.ok(aviso);
        }).orElseThrow(() -> new NotFoundException("Erro ao salvar Unidade"));
		
		
	}
	
	

	@GetMapping("/avisos")
	public List<Aviso>listAviso() {
		 return this.avisoService.listaAvisos();
		
		
		
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/avisos/{id}")
		public ResponseEntity<Aviso> get(@PathVariable Long id){
		Aviso aviso = this.avisoService.getById(id).get();
		
	
		return ResponseEntity.ok(aviso);
		}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/avisos/{id}")
		public ResponseEntity<Aviso> delete(@PathVariable Long id) {
		Optional<Aviso> aviso = this.avisoService.getById(id);
		if (aviso.isPresent()) {
			this.avisoService.remove(aviso.get().getId());
			return ResponseEntity.ok().body(aviso.get());
		} else {
			return ResponseEntity.noContent().build();
		}
			
		
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/avisos/{id}")
		public ResponseEntity<Aviso> edit(@PathVariable ("id") Long id, @RequestBody Aviso aviso){
		 return avisoService.getById(id)
		           .map(record -> {
		               record.setDescricaoAviso(aviso.getDescricaoAviso());
		               Aviso updated = avisoService.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
		}
		
		
	
}
