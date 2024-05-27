package co.edu.uniquindio.proyecto.sgre.controller;
import co.edu.uniquindio.proyecto.sgre.controller.service.IModelFactoryService;
import co.edu.uniquindio.proyecto.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.proyecto.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto.sgre.mapping.mappers.ventanaMapper;
import co.edu.uniquindio.proyecto.sgre.model.Empleado;
import co.edu.uniquindio.proyecto.sgre.model.Ventana;
import co.edu.uniquindio.proyecto.sgre.utils.Persistencia;
import co.edu.uniquindio.proyecto.sgre.utils.ventanaUtils;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController implements IModelFactoryService{
    Ventana ventana = new Ventana();
    ventanaMapper mapper= ventanaMapper.INSTANCE;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
//        ventana = ventanaUtils.inicializarDatos();
        cargarDatosBase();
        salvarDatosPrueba();

        //2. Cargar los datos de los archivos
		cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
//    	cargarResourceBinario();
//		guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
//		guardarResourceXML();
//        cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        if(ventana == null){
            cargarDatosBase();
            guardarResourceXML();
            cargarDatosDesdeArchivos();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

    private void cargarDatosDesdeArchivos() {
        try {
            Persistencia.cargarDatosArchivos(ventana);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarEmpleados(getBanco().getListaEmpleados());
            Persistencia.guardarRecursoVentanaXML(getBanco());
            Persistencia.guardarRecursoVentanaBinario(getBanco());
            //Persistencia.guardarClientes(getBanco().getListaClientes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosBase() {
        cargarResourceBinario();
    }

    public Ventana getBanco() {
        return ventana;
    }

    public void setBanco(Ventana Ventana) {
        this.ventana = Ventana;
    }

    @Override
    public List<EmpleadoDto> obtenerEmpleados() {
        return  mapper.getEmpleadosDto(ventana.getListaEmpleados());
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        try{
            if(!ventana.verificarEmpleadoExistente(empleadoDto.cedula())) {
                Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
                getBanco().agregarEmpleado(empleado);
            }
        salvarDatosPrueba();

            return true;
        }catch (EmpleadoException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String cedula) {
        boolean flagExiste = false;
        try {
            flagExiste = getBanco().eliminarEmpleado(cedula);
        } catch (EmpleadoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto) {
        try {
            Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
            getBanco().actualizarEmpleado(cedulaActual, empleado);
            return true;
        } catch (EmpleadoException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void cargarResourceXML() { ventana = Persistencia.cargarRecursoVentanaXML(); }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoVentanaXML(ventana);
    }

    private void cargarResourceBinario() { ventana = Persistencia.cargarRecursoVentanaBinario(); }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoVentanaBinario(ventana);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }


}
