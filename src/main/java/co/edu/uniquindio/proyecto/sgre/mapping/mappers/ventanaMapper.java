package co.edu.uniquindio.proyecto.sgre.mapping.mappers;


import co.edu.uniquindio.proyecto.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto.sgre.model.Empleado;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper
public interface ventanaMapper {
    ventanaMapper INSTANCE = Mappers.getMapper(ventanaMapper.class);

    @Named("empleadoToEmpleadoDto")
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);

    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    @IterableMapping(qualifiedByName = "empleadoToEmpleadoDto")
    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);

//    @Named("mappingToEmpeladoDto")
//    EmpleadoDto mappingToEmpeladoDto(Empleado empleado);

}
