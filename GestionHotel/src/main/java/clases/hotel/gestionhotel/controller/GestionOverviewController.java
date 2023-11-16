package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.MainApp;
import clases.hotel.gestionhotel.modelo.Persona;
import clases.hotel.gestionhotel.modelo.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionOverviewController {
    @FXML
    private TableView<Persona> tablaPersona;
    @FXML
    private TableColumn<Persona, String> persona;
    @FXML
    private Label dni;
    @FXML
    private Label nombre;
    @FXML
    private Label apellidos;
    @FXML
    private Label direccion;
    @FXML
    private Label localidad;
    @FXML
    private Label provincia;

    @FXML
    private TableView<Reserva> tablaReserva;
    @FXML
    private TableColumn<Reserva, String> reserva;
    @FXML
    private Label fechaL;
    @FXML
    private Label fechaS;
    @FXML
    private Label numH;
    @FXML
    private Label tipoH;
    @FXML
    private Label fum;
    @FXML
    private Label regAlo;

    private MainApp mainApp;

    public GestionOverviewController() {
    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        persona.setCellValueFactory(cellData -> cellData.getValue().DNIProperty());
        reserva.setCellValueFactory(cellData-> cellData.getValue().DNIClienteProperty());
        // Clear person details.
        showPersonDetails(null);
        showReservaDetails(null);
        // Listen for selection changes and show the person details when changed.
        tablaPersona.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

        tablaReserva.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue, newValue) -> showReservaDetails(newValue));
    }

    private void showReservaDetails(Reserva reserva) {
        if (reserva != null) {
            // Fill the labels with info from the person object.
            fechaL.setText(String.valueOf(reserva.getFechaLlegada()));
            fechaS.setText(String.valueOf(reserva.getFechaFin()));
            numH.setText(Integer.toString(reserva.getNumHabitacion()));
            tipoH.setText(reserva.getTipHab());
            String fuma="";
            if(reserva.isFumador()){
                fuma="SI";
            }else{
                fuma="NO";
            }
            fum.setText(fuma);
            regAlo.setText(reserva.getRegAloj());
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            fechaL.setText("");
            fechaS.setText("");
            numH.setText("");
            tipoH.setText("");
            fum.setText("");
            regAlo.setText("");
        }
    }



    private void showPersonDetails(Persona person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            dni.setText(person.getDNI());
            nombre.setText(person.getNombre());
            apellidos.setText(person.getApellidos());
            direccion.setText(Integer.toString(person.getDireccion()));
            localidad.setText(person.getLocalidad());
            provincia.setText(person.getProvincia());
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            dni.setText("");
            nombre.setText("");
            apellidos.setText("");
            direccion.setText("");
            localidad.setText("");
            provincia.setText("");
        }
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        tablaPersona.setItems(mainApp.getPersonData());
        tablaReserva.setItems(mainApp.getReservaData());
    }
}
