package br.com.acme.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.acme.service.AvisoService;

@RestController
@RequestMapping("/api")
public class AvisoController {


	@Autowired
	private AvisoService avisoService;
	


	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/avisos")
	public ResponseEntity<Aviso> cadastrar(@RequestBody Aviso aviso) {
		this.avisoService.save(aviso);
		
		return new ResponseEntity<Aviso>(aviso, HttpStatus.CREATED);
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
		               record.setCondominioAviso(aviso.getCondominioAviso());
		               Aviso updated = avisoService.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
		}
		
		
	
}

