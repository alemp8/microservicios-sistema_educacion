package microservicio.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import microservicio.commonsalumnos.models.entity.Alumnos;

public interface AlumnoRepository extends CrudRepository<Alumnos, Long> {

	
}
