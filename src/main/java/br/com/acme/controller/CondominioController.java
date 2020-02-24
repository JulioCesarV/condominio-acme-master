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

import br.com.acme.condominio.Condominio;
import br.com.acme.service.CondominioService;

@RestController
@RequestMapping("/api")
public class CondominioController {

	@Autowired
	private CondominioService condominioService;

	
	
	//Gerar multa aqui
	


	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/condominios")
	public ResponseEntity<Condominio> cadastrar(@RequestBody Condominio condominio) {
		this.condominioService.save(condominio);
		
		return new ResponseEntity<Condominio>(condominio, HttpStatus.CREATED);
	}
	
	

	@GetMapping("/condominios")
	public List<Condominio>listCond() {
		 return this.condominioService.listaCondominios();
		
		
		
	}
	
//	@GetMapping(value = "/condominios/{id_condominio}/unidades")
//    public ResponseEntity<Set<Unidade>> getUnidade_Condominio(@PathVariable("id_condominio") Long id) {
//        Optional<Condominio> optionalCondominio = this.condominioService.getById(id);
//        Condominio condominio = optionalCondominio.get();
//        if (!optionalCondominio.isPresent()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(condominio.getUnidadesCondominio());
//        }
//
//    }
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/condominios/{id}")
		public ResponseEntity<Condominio> get(@PathVariable ("id") Long id){
		Condominio condominio = this.condominioService.getById(id).get();
		return ResponseEntity.ok(condominio);
//		Optional<Condominio> optionalCondominio = this.condominioService.getById(id);
//        return optionalCondominio.map(response -> ResponseEntity.ok().body(response))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/condominios/{id}")
		public ResponseEntity<Condominio> delete(@PathVariable Long id) {
		Optional<Condominio> condominio = this.condominioService.getById(id);
		if (condominio.isPresent()) {
			this.condominioService.remove(condominio.get().getId());
			return ResponseEntity.ok().body(condominio.get());
		} else {
			return ResponseEntity.noContent().build();
		}
			
		
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/condominios/{id}")
		public ResponseEntity<Condominio> edit(@PathVariable ("id") Long id, @RequestBody Condominio condominio){
		 return condominioService.getById(id)
		           .map(record -> {
		               record.setNome(condominio.getNome());
		               record.setEmail(condominio.getEmail());
		               record.setTelefone(condominio.getTelefone());
		               Condominio updated = condominioService.save(record);
		               return ResponseEntity.ok().body(updated);
		           }).orElse(ResponseEntity.notFound().build());
		}
		
		
	
}
