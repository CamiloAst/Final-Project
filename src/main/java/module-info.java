module co.edu.uniquindio.proyecto.sgre {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mapstruct;
    requires java.logging;
    requires java.desktop;

    opens co.edu.uniquindio.proyecto.sgre to javafx.fxml;

    exports co.edu.uniquindio.proyecto.sgre;
    exports co.edu.uniquindio.proyecto.sgre.viewController;

    opens co.edu.uniquindio.proyecto.sgre.viewController to javafx.fxml;

    exports  co.edu.uniquindio.proyecto.sgre.controller;
    exports co.edu.uniquindio.proyecto.sgre.mapping.dto;
    exports co.edu.uniquindio.proyecto.sgre.mapping.mappers;
    exports co.edu.uniquindio.proyecto.sgre.model;

    opens co.edu.uniquindio.proyecto.sgre.controller to javafx.fxml;
}