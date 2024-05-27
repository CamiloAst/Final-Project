package co.edu.uniquindio.proyecto.sgre.viewController;

import co.edu.uniquindio.proyecto.sgre.controller.EmpleadoController;
import co.edu.uniquindio.proyecto.sgre.mapping.dto.EmpleadoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class empleadoViewController {
    EmpleadoController empleadoControllerService;
    ObservableList<EmpleadoDto> listaEmpleadosDto = FXCollections.observableArrayList();
    EmpleadoDto empleadoSeleccionado;
    @FXML
    private ComboBox<String> ComboBoxRoles;

    @FXML
    private TableColumn<EmpleadoDto, String> tcRoles;
    ObservableList<String> listaRoles = FXCollections.observableArrayList("Mesero", "Presentador", "Secretario");


    @FXML
        private ResourceBundle resources;
        @FXML
        private URL location;

        @FXML
        private Button btnActualizar;

        @FXML
        private Button btnAgregrar;

        @FXML
        private Button btnEliminar;

        @FXML
        private Button btnNuevo;

        @FXML
        private TableView<EmpleadoDto> tblEmpleado;
        @FXML
        private TableColumn<EmpleadoDto, String> tcNombre;

        @FXML
        private TableColumn<EmpleadoDto, String> tcIdentificacion;

        @FXML
        private TableColumn<EmpleadoDto, String> tcCorreo;

        @FXML
        private TextField txtCorreo;

        @FXML
        private TextField txtIdentificacion;

        @FXML
        private TextField txtNombre;

        @FXML
        void actualizarEmpleadoAction(ActionEvent event) { actualizarEmpleado(); }
        @FXML
        void agregarEmpleadoAction(ActionEvent event) {crearEmpleado();}
        @FXML
        void eliminarEmpleadoAction(ActionEvent event) { eliminarEmpleado();}

        @FXML
        void nuevoEmpleadoAction(ActionEvent event) {

            txtNombre.setText("Ingrese el nombre");

            txtIdentificacion.setText("Ingrese la cedula");

            txtCorreo.setText("Ingrese el correo");
        }


    @FXML
    void initialize() {
        empleadoControllerService = new EmpleadoController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerEmpleados();
        tblEmpleado.getItems().clear();
        tblEmpleado.setItems(listaEmpleadosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));

    }

    private void obtenerEmpleados() {
        listaEmpleadosDto.addAll(empleadoControllerService.obtenerEmpleados());
    }

    private void listenerSelection() {
        tblEmpleado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            mostrarInformacionEmpleado(empleadoSeleccionado);
        });
    }

    private void mostrarInformacionEmpleado(EmpleadoDto empleadoSeleccionado) {
        if(empleadoSeleccionado != null){
            txtNombre.setText(empleadoSeleccionado.nombre());
            txtIdentificacion.setText(empleadoSeleccionado.cedula());
            txtCorreo.setText(empleadoSeleccionado.correo());

        }
    }

    private void crearEmpleado() {
        //1. Capturar los datos
        EmpleadoDto empleadoDto = construirEmpleadoDto();
        //2. Validar la información
        if(datosValidos(empleadoDto)){
            if(empleadoControllerService.agregarEmpleado(empleadoDto)){
                listaEmpleadosDto.add(empleadoDto);
                mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarEmpleado() {
        boolean empleadoEliminado = false;
        if(empleadoSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al empleado?")){
                empleadoEliminado = empleadoControllerService.eliminarEmpleado(empleadoSeleccionado.cedula());
                if(empleadoEliminado == true){
                    listaEmpleadosDto.remove(empleadoSeleccionado);
                    empleadoSeleccionado = null;
                    tblEmpleado.getSelectionModel().clearSelection();
                    limpiarCamposEmpleado();
                    mostrarMensaje("Notificación empleado", "Empleado eliminado", "El empleado se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no eliminado", "El empleado no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no seleccionado", "Seleccionado un empleado de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarEmpleado() {
        boolean clienteActualizado = false;
        //1. Capturar los datos
        String cedulaActual = empleadoSeleccionado.cedula();
        EmpleadoDto empleadoDto = construirEmpleadoDto();
        //2. verificar el empleado seleccionado
        if(empleadoSeleccionado != null){
            //3. Validar la información
            if(datosValidos(empleadoSeleccionado)){
                clienteActualizado = empleadoControllerService.actualizarEmpleado(cedulaActual,empleadoDto);
                if(clienteActualizado){
                    listaEmpleadosDto.remove(empleadoSeleccionado);
                    listaEmpleadosDto.add(empleadoDto);
                    tblEmpleado.refresh();
                    mostrarMensaje("Notificación empleado", "Empleado actualizado", "El empleado se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposEmpleado();
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no actualizado", "El empleado no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }
        private EmpleadoDto construirEmpleadoDto() {
            return new EmpleadoDto(
                    txtNombre.getText(),
                    txtIdentificacion.getText(),
                    txtCorreo.getText()
            );
        }


    private void limpiarCamposEmpleado() {
        txtNombre.setText("");

        txtIdentificacion.setText("");

        txtCorreo.setText("");

    }

    private boolean datosValidos(EmpleadoDto empleadoDto) {
        String mensaje = "";
        if(empleadoDto.nombre() == null || empleadoDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;

        if(empleadoDto.cedula() == null || empleadoDto.cedula().equals(""))
            mensaje += "El documento es invalido \n" ;

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static void llenarBox(ComboBox<String> boxRoles, ObservableList<String> roles){
            boxRoles.setItems(roles);
    }
    public void llenarRoles() {
            llenarBox(ComboBoxRoles, listaRoles);
    }
}

