package microservicio.cursos.models.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import microservicio.cursos.models.entity.Cursos;

public interface CursoRepository extends CrudRepository<Cursos, Long> {

	@Query("SELECT c FROM Cursos c JOIN FETCH c.alumnos a WHERE a.id = ?1")
	public Cursos findCursoByAlumno(Long id);

}
