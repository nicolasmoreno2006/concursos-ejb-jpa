package co.edu.unipiloto.concursos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Curso, extension del caso de estudio de concursos.
 *
 * Columnas requeridas: codigo de curso (llave primaria), nombre del curso,
 * numero de creditos, semestre y numero de estudiantes admitidos.
 *
 * La relacion muchos-a-muchos con Estudiante se materializa en la tabla
 * intermedia CURSO_ESTUDIANTE (curso_codigo, estudiantes_id), generada
 * automaticamente por JPA a partir de @JoinTable.
 */
@Entity
public class Curso implements Serializable {

    @Id
    private String codigoCurso;

    private String nombreCurso;
    private int numeroCreditos;
    private String semestre;
    private int numeroEstudiantesAdmitidos;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "CURSO_ESTUDIANTE",
            joinColumns = @JoinColumn(name = "curso_codigo"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private Set<Estudiante> estudiantes = new HashSet<>();

    public Curso() {
    }

    public Curso(String codigoCurso, String nombreCurso, int numeroCreditos,
                 String semestre, int numeroEstudiantesAdmitidos) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.numeroCreditos = numeroCreditos;
        this.semestre = semestre;
        this.numeroEstudiantesAdmitidos = numeroEstudiantesAdmitidos;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getNumeroEstudiantesAdmitidos() {
        return numeroEstudiantesAdmitidos;
    }

    public void setNumeroEstudiantesAdmitidos(int numeroEstudiantesAdmitidos) {
        this.numeroEstudiantesAdmitidos = numeroEstudiantesAdmitidos;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
