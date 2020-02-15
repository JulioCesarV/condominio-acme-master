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

import br.com.acme.multas.Multa;
import br.com.acme.service.MultaService;

@RestController
@RequestMapping("/api")
public class MultaController {

	@Autowired
	private MultaService multaService;
	


	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/multas")
	public ResponseEntity<Multa> cadastrar(@RequestBody Multa multa) {
		this.multaService.save(multa);
		
		return new ResponseEntity<Multa>(multa, HttpStatus.CREATED);
	}
	
	

	@GetMapping("/multas")
	public List<Multa>listaMultas() {
		 return this.multaService.listaMultas();
		
		
		
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/multas/{id}")
		public ResponseEntity<Multa> get(@PathVariable Long id){
		Multa multa = this.multaService.getById(id).get();
		
	
		return ResponseEntity.ok(multa);
		}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/multas/{id}")
		public ResponseEntity<Multa> delete(@PathVariable Long id) {
		Optional<Multa> multa = this.multaService.getById(id);
		if (multa.isPresent()) {
			this.multaService.remove(multa.get().getId());
			return ResponseEntity.ok().body(multa.get());
		} else {
			return ResponseEntity.noContent().build();
		}
			
		
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/multas/{id}")
		public ResponseEntity<Multa> edit(@PathVariable ("id") Long id, @RequestBody Multa multa){
		 return multaService.getById(id)
		           .map(record -> {
		               record.setDescricaoMulta(multa.getDescricaoMulta());
		               record.setDataMulta(multa.getDataMulta());
		               record.setValorMulta(multa.getValorMulta());
		               record.setCondominioMulta(multa.getCondominioMulta());
		               Multa updated = multaService.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
		}
		
		
	
}

