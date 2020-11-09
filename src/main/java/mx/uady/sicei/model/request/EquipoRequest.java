package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import mx.uady.sicei.model.Licenciatura;

public class EquipoRequest {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String modelo;

    public EquipoRequest() {
    }

    public EquipoRequest(String modelo) {
        this.modelo = modelo;
    }

    public String getmodelo() {
        return this.modelo;
    }

    public void setmodelo(String modelo) {
        this.modelo = modelo;
    }

    public EquipoRequest modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " modelo='" + getmodelo() + "'" +
            "}";
    }
    
}
