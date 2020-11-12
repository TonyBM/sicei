package mx.uady.sicei.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import javax.persistence.EmbeddedId;

import mx.uady.sicei.model.Tutoriallave;

@Entity
@Table(name = "tutorias")
public class Tutoria {

    @EmbeddedId
    private Tutoriallave id;

    @Column
    private Integer horas;

    @ManyToOne(optional=false)
    @JoinColumn(name="id_alumno",referencedColumnName="id", insertable=false, updatable=false)
    Alumno alumno;

    @ManyToOne(optional=false)
    @JoinColumn(name="id_profesor",referencedColumnName="id", insertable=false, updatable=false)
    Profesor profesor;

    public Tutoria() { }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setId(Tutoriallave id) {
        this.id = id;
    }

    public Tutoriallave getId() {
        return id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
