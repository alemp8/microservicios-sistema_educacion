package microservicio.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservicio.commons.services.CommonService;
import microservicio.commons.services.CommonServiceImpl;
import microservicio.cursos.clients.RespuestaFeignClient;
import microservicio.cursos.models.entity.Cursos;
import microservicio.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Cursos,CursoRepository> implements CursoService {
	@Autowired
	private RespuestaFeignClient client;
	@Autowired
	private CursoRepository repository;

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

	@Override
	@Transactional(readOnly=true)
	public Cursos findCursoByAlumno(Long id) {
		
		return repository.findCursoByAlumno(id);
	}
}
