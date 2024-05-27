package co.edu.uniquindio.proyecto.sgre.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    Empleado empleadoAsociado;



    public Cliente() {

    }


    public Empleado getEmpleadoAsociado() {
        return empleadoAsociado;
    }


    public void setEmpleadoAsociado(Empleado empleadoAsociado) {
        this.empleadoAsociado = empleadoAsociado;
    }



}
