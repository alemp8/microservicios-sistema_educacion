package microservicio.usuarios.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import microservicio.commonsalumnos.models.entity.Alumnos;
import microservicio.usuarios.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	/*Inyectamos el AlumnoRepository, usando Spring Data JPA y crud repository nos es mas facil poder realizar
	 las operaciones del CRUD
	*/
	@Autowired
	public AlumnoRepository repositorio;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumnos> findAll() {
		
		return repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumnos> findById(Long id) {
		
		return repositorio.findById(id);
	}

	@Override
	public Alumnos save(Alumnos alumno) {
		
		return repositorio.save(alumno);
	}

	@Override
	public void deleteById(Long id) {
		repositorio.deleteById(id);
		
	}

}
