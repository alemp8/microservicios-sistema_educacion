package microservicio.examenes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import microservicio.commons.controllers.CommonController;
import microservicio.commonsexamenes.models.entity.Examen;
import microservicio.commonsexamenes.models.entity.Preguntas;
import microservicio.examenes.services.ExamenService;

@RestController
public class ExamenController extends CommonController<Examen,ExamenService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Validated @RequestBody Examen examen , BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return validar(result);
		}
		
		Optional <Examen> o = service.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDB = o.get();
		examenDB.setNombre(examen.getNombre());
		
		List<Preguntas> eliminadas = new ArrayList();
		examenDB.getPregunta().forEach(pdb->{
			if(!(examen.getPregunta().contains(pdb))) {
				eliminadas.add(pdb);
			}
		});
		
		eliminadas.forEach(p->{
			examenDB.removePregunta(p);
		});
		
		examenDB.setPregunta(examen.getPregunta());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
		
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas (){
		return ResponseEntity.ok(service.findAllAsignaturas());
		
	}
}
