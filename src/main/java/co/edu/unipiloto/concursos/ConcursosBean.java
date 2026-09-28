package co.edu.unipiloto.concursos;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Capa de logica de negocio (EJB de sesion sin estado) que administra la
 * capa de persistencia (JPA/EclipseLink) del caso de estudio de concursos.
 */
@Stateless
public class ConcursosBean {

    @PersistenceContext(unitName = "ConcursosPU")
    private EntityManager em;

    public void crearEstudiante(String nombre, String email) {
        Estudiante estudiante = new Estudiante(nombre, email);
        em.persist(estudiante);
    }

    public void crearCurso(String codigo, String nombre, int creditos,
                            String semestre, int cupo) {
        Curso curso = new Curso(codigo, nombre, creditos, semestre, cupo);
        em.persist(curso);
    }

    /**
     * Matricula un estudiante en un curso, validando que el curso no haya
     * superado el numero de estudiantes admitidos.
     */
    public void inscribir(Integer estudianteId, String codigoCurso) {
        Estudiante estudiante = em.find(Estudiante.class, estudianteId);
        Curso curso = em.find(Curso.class, codigoCurso);

        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no existe.");
        }
        if (curso == null) {
            throw new IllegalArgumentException("El curso no existe.");
        }
        if (curso.getEstudiantes().contains(estudiante)) {
            throw new IllegalStateException("El estudiante ya esta inscrito en este curso.");
        }
        if (curso.getEstudiantes().size() >= curso.getNumeroEstudiantesAdmitidos()) {
            throw new IllegalStateException("El curso ya alcanzo el cupo maximo de estudiantes.");
        }

        curso.getEstudiantes().add(estudiante);
        estudiante.getCursos().add(curso);
    }

    public List<Estudiante> listarEstudiantes() {
        return em.createQuery("SELECT e FROM Estudiante e ORDER BY e.nombre", Estudiante.class)
                .getResultList();
    }

    public List<Curso> listarCursos() {
        return em.createQuery("SELECT c FROM Curso c ORDER BY c.codigoCurso", Curso.class)
                .getResultList();
    }
}
