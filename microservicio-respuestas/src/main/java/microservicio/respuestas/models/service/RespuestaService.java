package microservicio.respuestas.models.service;

import microservicio.respuestas.models.entity.Respuesta;

public interface RespuestaService {
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	public Iterable<Respuesta> findExamenesIdsconRespuestaByAlumno(Long alumnoId);
}
