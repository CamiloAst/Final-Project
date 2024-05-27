package co.edu.uniquindio.proyecto.sgre.controller.service;

import co.edu.uniquindio.proyecto.sgre.mapping.dto.EmpleadoDto;

import java.util.List;

public interface IModelFactoryService {
    List<EmpleadoDto> obtenerEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleadoDto);

    boolean eliminarEmpleado(String cedula);

    boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto);

}
