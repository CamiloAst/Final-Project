package co.edu.uniquindio.proyecto.sgre.viewController;

import co.edu.uniquindio.proyecto.sgre.controller.service.IVentanaControllerService;
import co.edu.uniquindio.proyecto.sgre.controller.ventanaController;
import javafx.fxml.FXML;

public class ventanaViewController {
    IVentanaControllerService ventanaControllerService;

    @FXML
    void initialize() {
        ventanaControllerService = new ventanaController();
    }

}
