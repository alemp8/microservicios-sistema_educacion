package microservicio.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import microservicio.commonsalumnos.models.entity.Alumnos;
import microservicio.commonsexamenes.models.entity.Examen;
import microservicio.cursos.models.entity.Cursos;
import microservicio.cursos.services.CursoService;
import microservicio.cursos.services.CursoServiceImpl;

@RestController
public class CursoController extends CommonController<Cursos, CursoServiceImpl> {
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador",balanceadorTest);
		response.put("cursos",service.findAll());
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Validated @RequestBody Cursos curso,BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return validar(result);
		}
		
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos cursoDB = o.get();
		cursoDB.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumno(@RequestBody List<Alumnos> alumnos, @PathVariable Long id){
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos cursoDB = o.get();
		alumnos.forEach(a->{
			cursoDB.addAlumno(a);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-alumnos")
	public ResponseEntity<?> removerAlumno(@RequestBody Alumnos alumnos, @PathVariable Long id){
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos cursoDB = o.get();
		
		cursoDB.removeAlumno(alumnos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@PutMapping("/{id}/asignar-examen")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examen, @PathVariable Long id){
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos cursoDB = o.get();
		examen.forEach(a->{
			cursoDB.addExamen(a);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> removerExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos cursoDB = o.get();
		
		cursoDB.removeExamen(examen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
	    Cursos curso = service.findCursoByAlumno(id);
	    if (curso != null) {
	        List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);
	        List<Examen> examenes = curso.getExamen().stream().map(examen -> {
	            if (examenesIds.contains(examen.getId())) {
	                examen.setRespondido(true);
	            }
	            return examen;
	        }).collect(Collectors.toList());
	        curso.setExamen(examenes);
	    }
	    return ResponseEntity.ok(curso);
	}

}
