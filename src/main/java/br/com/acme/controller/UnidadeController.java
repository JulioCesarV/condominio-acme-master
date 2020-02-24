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

import br.com.acme.exception.NotFoundException;
import br.com.acme.service.CondominioService;
import br.com.acme.service.UnidadeService;
import br.com.acme.unidade.Unidade;

@RestController
@RequestMapping("/api")
public class UnidadeController {

	@Autowired
	private UnidadeService unidadeService;
	
	@Autowired
	private CondominioService condominioService;


	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/condominios/{id_condominio}/unidades")
	public ResponseEntity<Unidade> cadastrar(@PathVariable ("id_condominio") Long id, @Valid @RequestBody Unidade unidade) {
		return this.condominioService.getById(id).map(condominio -> {
            unidade.setCondominioUnidade(condominio);
            unidadeService.save(unidade);
            return ResponseEntity.ok(unidade);
        }).orElseThrow(() -> new NotFoundException("Erro ao salvar Unidade"));
		
		
	}
	
	

	@GetMapping("/unidades")
	public List<Unidade>listUnidade() {
		 return this.unidadeService.listaUnidades();
		
		
		
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/unidades/{id}")
		public ResponseEntity<Unidade> get(@PathVariable Long id){
		Unidade unidade = this.unidadeService.getById(id).get();
		
	
		return ResponseEntity.ok(unidade);
		}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/unidades/{id}")
		public ResponseEntity<Unidade> delete(@PathVariable Long id) {
		Optional<Unidade> unidade = this.unidadeService.getById(id);
		if (unidade.isPresent()) {
			this.unidadeService.remove(unidade.get().getId());
			return ResponseEntity.ok().body(unidade.get());
		} else {
			return ResponseEntity.noContent().build();
		}
			
		
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/unidades/{id}")
		public ResponseEntity<Unidade> edit(@PathVariable ("id") Long id, @RequestBody Unidade unidade){
		 return unidadeService.getById(id)
		           .map(record -> {
		               record.setNumeroUnidade(unidade.getNumeroUnidade());
		               record.setBlocoUnidade(unidade.getBlocoUnidade());
		               Unidade updated = unidadeService.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
		}
		
		
	
}
