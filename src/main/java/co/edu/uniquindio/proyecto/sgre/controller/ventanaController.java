package co.edu.uniquindio.proyecto.sgre.controller;

import co.edu.uniquindio.proyecto.sgre.controller.service.IVentanaControllerService;

public class ventanaController  implements IVentanaControllerService{
    ModelFactoryController modelFactoryController;
    public ventanaController(){
        System.out.println("Llamando al singleton desde ventanaServiceController");
        modelFactoryController = ModelFactoryController.getInstance();

    }
}
