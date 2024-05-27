package co.edu.uniquindio.proyecto.sgre;

import co.edu.uniquindio.proyecto.sgre.controller.ModelFactoryController;
import co.edu.uniquindio.proyecto.sgre.mapping.dto.EmpleadoDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class mainController {
    public static void main(String[] args) {
        ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();


        EmpleadoDto empleadoDto = new EmpleadoDto(
                "juan",
                "arias",
                "juanArias@gmail.com"

        );

        if (modelFactoryController.agregarEmpleado(empleadoDto)) {
            System.out.println("No existe se agrego correctamente");
        } else {
            System.out.println("Ya existe");
        }

        List<EmpleadoDto> empleadoDtoList = modelFactoryController.obtenerEmpleados();
        empleadoDtoList.forEach(System.out::println);

    }
}
