package microservicio.cursos.models.entity;

import microservicio.commonsalumnos.models.entity.Alumnos;
import microservicio.commonsexamenes.models.entity.Examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="cursos")
public class Cursos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creteAt;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumnos> alumnos;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;
	
	@PrePersist
	public void prePersist() {
		this.creteAt = new Date();
	}
	

	public Cursos() {
		this.alumnos=new ArrayList<>();
		this.examenes=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreteAt() {
		return creteAt;
	}

	public void setCreteAt(Date creteAt) {
		this.creteAt = creteAt;
	}
	public List<Alumnos> getAlumnos() {
		return alumnos;
	}

	public void addAlumno (Alumnos alumnos) {
		this.alumnos.add(alumnos);
	}

	public void removeAlumno (Alumnos alumnos) {
		this.alumnos.remove(alumnos);
	}


	public List<Examen> getExamen() {
		return examenes;
	}


	public void setExamen(List<Examen> examen) {
		this.examenes = examen;
	}
	
	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}


	public void setAlumnos(List<Alumnos> alumnos) {
		this.alumnos = alumnos;
	}
	
	

}
