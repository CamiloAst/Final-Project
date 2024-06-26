package co.edu.uniquindio.proyecto.sgre.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

        private Double salario;
        private String codigo;
        ArrayList<Cliente> listaClientesAsociados = new ArrayList<Cliente>();
        public Empleado() {

        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public Double getSalario() {
            return salario;
        }


        public void setSalario(Double salario) {
            this.salario = salario;
        }


        public ArrayList<Cliente> getListaClientesAsociados() {
            return listaClientesAsociados;
        }


        public void setListaClientesAsociados(ArrayList<Cliente> listClientesAsociados) {
            this.listaClientesAsociados = listClientesAsociados;
        }

    }

