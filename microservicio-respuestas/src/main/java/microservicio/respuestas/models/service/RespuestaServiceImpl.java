package microservicio.respuestas.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservicio.respuestas.models.entity.Respuesta;
import microservicio.respuestas.models.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService{

	@Autowired
	private RespuestaRepository repository;
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		return repository.saveAll(respuestas);
	}
	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}
	@Override
	public Iterable<Respuesta> findExamenesIdsconRespuestaByAlumno(Long alumnoId) {
		
		return repository.findExamenesIdsconRespuestaByAlumno(alumnoId);
	}

}
