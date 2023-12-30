package microservicio.cursos.services;

import microservicio.commons.services.CommonService;
import microservicio.cursos.models.entity.Cursos;

public interface CursoService extends CommonService<Cursos> {
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	public Cursos findCursoByAlumno(Long id);
}
