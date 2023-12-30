package microservicio.usuarios.models.services;

import java.util.Optional;

import microservicio.commonsalumnos.models.entity.Alumnos;

public interface AlumnoService {
    //Definimos los metodos para buscar por id o buscar todos asi como guardar y eliminar
	public Iterable<Alumnos> findAll();
	public Optional<Alumnos> findById(Long id);
	public Alumnos save (Alumnos alumno);
	public void deleteById(Long id);

}
