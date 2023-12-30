package microservicio.respuestas.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import microservicio.commonsalumnos.models.entity.Alumnos;
import microservicio.commonsexamenes.models.entity.Preguntas;

@Entity
@Table(name="respuestas")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String texto;
	@OneToOne(fetch = FetchType.LAZY)
	private Preguntas pregunta;
	@ManyToOne(fetch = FetchType.LAZY)
	private Alumnos alumno;
	
	public Preguntas getPregunta() {
		return pregunta;
	}
	public void setPregunta(Preguntas pregunta) {
		this.pregunta = pregunta;
	}
	public Alumnos getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
