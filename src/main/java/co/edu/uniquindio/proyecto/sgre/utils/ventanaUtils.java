package co.edu.uniquindio.proyecto.sgre.utils;

import co.edu.uniquindio.proyecto.sgre.model.Persona;
import co.edu.uniquindio.proyecto.sgre.model.Cliente;
import co.edu.uniquindio.proyecto.sgre.model.Empleado;
import co.edu.uniquindio.proyecto.sgre.model.Ventana;

public class ventanaUtils {

    public static Ventana inicializarDatos() {
        Ventana ventana = new Ventana();

        Cliente cliente = new Cliente();
        cliente.setNombre("juan");
        cliente.setApellido("arias");
        cliente.setCedula("125454");
        cliente.setDireccion("Armenia");
        cliente.setCorreo("uni1@");
        cliente.setFechaNacimiento("12454");
        cliente.setTelefono("125444");
        //ventana.getListaClientes().add(cliente);

        cliente = new Cliente();
        cliente.setNombre("Pedro");
        cliente.setApellido("Perez");
        cliente.setCedula("77787");
        cliente.setDireccion("Pererira");
        cliente.setCorreo("uni2@");
        cliente.setFechaNacimiento("12454");
        cliente.setTelefono("125444");
        //ventana.getListaClientes().add(cliente);

        cliente = new Cliente();
        cliente.setNombre("Alberto");
        cliente.setCedula("12555");
        cliente.setCorreo("uni3@");


        //ventana.getListaClientes().add(cliente);


        Empleado empleado = new Empleado();
        empleado.setNombre("juan");
        empleado.setCedula("125454");
        empleado.setCorreo("aasfa@gmail.om");
        ventana.getListaEmpleados().add(empleado);

        empleado = new Empleado();
        empleado.setNombre("Ana");
        empleado.setCedula("125454");
        empleado.setCorreo("ana@gmail.om");
        ventana.getListaEmpleados().add(empleado);

        empleado = new Empleado();
        empleado.setNombre("Pedro");
        empleado.setCedula("125454");
        empleado.setCorreo("aasfa@gmail.om");
        ventana.getListaEmpleados().add(empleado);
        System.out.println("Información del banco creada");
        return ventana;
    }
}

