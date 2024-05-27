package co.edu.uniquindio.proyecto.sgre.utils;


import co.edu.uniquindio.proyecto.sgre.exceptions.usuarioException;
import co.edu.uniquindio.proyecto.sgre.model.*;
import co.edu.uniquindio.proyecto.sgre.model.Cliente;
import co.edu.uniquindio.proyecto.sgre.model.Ventana;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;




public class Persistencia {


    //bancoUq/src/main/resources/persistencia/archivoClientes.txt
    //public static final String RUTA_ARCHIVO_CLIENTES = "src/main/resources/persistencia/archivoClientes.txt";
    public static final String RUTA_ARCHIVO_EMPLEADOS = "src/main/resources/persistencia/archivoEmpleados.txt";
    //public static final String RUTA_ARCHIVO_USUARIOS = "/src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/VentanaLog.txt";
    //public static final String RUTA_ARCHIVO_OBJETOS = "co.edu.uniquindio.proyecto/src/main/resources/persistencia/archivoObjetos.txt";
    public static final String RUTA_ARCHIVO_MODELO_VENTANA_BINARIO = "src/main/resources/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_VENTANA_XML = "src/main/resources/persistencia/model.xml";
//	C:\td\persistencia


    public static void cargarDatosArchivos(Ventana ventana) throws FileNotFoundException, IOException {
        //cargar archivo de clientes
        /**
        ArrayList<Cliente> clientesCargados = cargarClientes();

        if (clientesCargados.size() > 0)
            ventana.getListaClientes().addAll(clientesCargados);
        */
        //cargar archivos empleados
        ArrayList<Empleado> empleadosCargados = cargarEmpleados();
        if (!empleadosCargados.isEmpty())
        {
            ventana.getListaEmpleados().addAll(empleadosCargados);
        }


        //cargar archivo eventos

        //cargar archivo reserva

        //cargar archivo usuario

    }

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     *
     * @param
     * @param
     * @throws IOException
     */
    /**
    public static void guardarClientes(ArrayList<Cliente> listaClientes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Cliente cliente : listaClientes) {
            contenido += cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getCedula() + "," + cliente.getDireccion()
                    + "," + cliente.getCorreo() + "," + cliente.getFechaNacimiento() + "," + cliente.getTelefono() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
    }
    */

    public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados) throws IOException {
        String contenido = "";
        for (Empleado empleado : listaEmpleados) {
            contenido += empleado.getNombre() +
                    "," + empleado.getCedula() +
                    "," + empleado.getCorreo() +"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
    }


//	----------------------LOADS------------------------

    /**
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */

    /**
    public static ArrayList<Cliente> cargarClientes() throws FileNotFoundException, IOException {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Cliente cliente = new Cliente();
            cliente.setNombre(linea.split(",")[0]);
            cliente.setApellido(linea.split(",")[1]);
            cliente.setCedula(linea.split(",")[2]);
            cliente.setDireccion(linea.split(",")[3]);
            cliente.setCorreo(linea.split(",")[4]);
            cliente.setFechaNacimiento(linea.split(",")[5]);
            cliente.setTelefono(linea.split(",")[6]);
            clientes.add(cliente);
        }
        return clientes;
    }
    */

    public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Empleado empleado = new Empleado();
            empleado.setNombre(linea.split(",")[0]);
            empleado.setCedula(linea.split(",")[1]);
            empleado.setCorreo(linea.split(",")[2]);
            empleados.add(empleado);
        }
        return empleados;
    }


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    /**
    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, usuarioException {

        if (validarUsuario(usuario, contrasenia)) {
            return true;
        } else {
            throw new usuarioException("Usuario no existe");
        }

    }

    private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);

        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if (usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
        String linea = "";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);

            Usuario usuario = new Usuario();
            usuario.setUsuario(linea.split(",")[0]);
            usuario.setContrasenia(linea.split(",")[1]);

            usuarios.add(usuario);
        }
        return usuarios;
    }
    /*

//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     *
     * @param
     * @param ruta
     * @throws IOException
     */
    /**
    public static void guardarObjetos(ArrayList<Cliente> listaClientes, String ruta) throws IOException {
        String contenido = "";

        for (Cliente clienteAux : listaClientes) {
            contenido += clienteAux.getNombre() + "," + clienteAux.getApellido() + "," + clienteAux.getCedula() + clienteAux.getDireccion()
                    + "," + clienteAux.getCorreo() + "," + clienteAux.getFechaNacimiento() + "," + clienteAux.getTelefono() + "\n";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, true);
    }
    */

    //------------------------------------SERIALIZACIÓN  y XML


    public static Ventana cargarRecursoVentanaBinario() {

        Ventana ventana = null;

        try {
            ventana = (Ventana) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_VENTANA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ventana;
    }

    public static void guardarRecursoVentanaBinario(Ventana ventana) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_VENTANA_BINARIO, ventana);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Ventana cargarRecursoVentanaXML() {

        Ventana ventana = null;

        try {
            ventana = (Ventana) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_VENTANA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ventana;

    }


    public static void guardarRecursoVentanaXML(Ventana ventana) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_VENTANA_XML, ventana);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
