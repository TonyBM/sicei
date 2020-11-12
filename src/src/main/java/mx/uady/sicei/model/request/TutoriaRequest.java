package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TutoriaRequest {

    @NotNull
    @Min(value = 1, message="valor tiene que tener un minimo de 1 hora")
    @Max(value= 2, message="valor no puede exceder las 2 horas")
    private Integer horas;
    
    @NotNull
    private Integer idAlumno;
    
    @NotNull
    private Integer idProfesor;

    public TutoriaRequest() {

    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public String toString() {
        return "{" +
            "horas='" + getHoras() + "'" +
            ", idalumno='" + getIdAlumno() + "'" +
            ", idprofesor='" + getIdProfesor() + "'" +
            "}";
    }
}