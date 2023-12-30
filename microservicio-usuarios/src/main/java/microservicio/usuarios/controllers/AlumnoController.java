package microservicio.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import microservicio.commonsalumnos.models.entity.Alumnos;
import microservicio.usuarios.models.services.AlumnoService;

@RestController
public class AlumnoController {
	//Inyectamos el service
	@Autowired
	private AlumnoService service;
	
	@GetMapping("/upload/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		Optional<Alumnos> o = service.findById(id);
		if(o.isEmpty()||o.get().getFoto()==null) {
			return ResponseEntity.notFound().build();
		}
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){ 
		Optional<Alumnos> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumnos alumno){
		Alumnos alumnoDB = service.save(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumnos alumno, @PathVariable Long id){
	    Optional<Alumnos> o = service.findById(id);
	    if(o.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    Alumnos alumnoDB = o.get();
	    alumnoDB.setNombre(alumno.getNombre());
	    alumnoDB.setApellido(alumno.getApellido());
	    alumnoDB.setEmail(alumno.getEmail());
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearconfoto ( Alumnos alumno, @RequestParam MultipartFile archivo ) throws IOException{
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return crear(alumno);
		
	}
	
	@PutMapping("/editarfoto/{id}")
	public ResponseEntity<?> editarfoto(Alumnos alumno, @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException{
	    Optional<Alumnos> o = service.findById(id);
	    if(o.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    Alumnos alumnoDB = o.get();
	    alumnoDB.setNombre(alumno.getNombre());
	    alumnoDB.setApellido(alumno.getApellido());
	    alumnoDB.setEmail(alumno.getEmail());
	    
	    if(!archivo.isEmpty()) {
			alumnoDB.setFoto(archivo.getBytes());
		}
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
}
