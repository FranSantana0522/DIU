package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.MainApp;
import clases.hotel.gestionhotel.modelo.Persona;
import clases.hotel.gestionhotel.modelo.Reserva;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class GestionOverviewController {
    @FXML
    private TableView<Persona> tablaPersona;
    @FXML
    private TableColumn<Persona, String> nombreC;
    @FXML
    private TableColumn<Persona, String> apellido;
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
    private TableColumn<Reserva, Integer> id;
    @FXML
    private TableColumn<Reserva, LocalDate> fechaI;
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
    private Integer i;
    private Integer iR;
    private MainApp mainApp;

    /**
     * Constructor del gestion overview vacio
     */
    public GestionOverviewController() {
    }

    /**
     * Se ejecuta al iniciar el controlador
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellido.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        id.setCellValueFactory(cellData-> cellData.getValue().idProperty().asObject());
        fechaI.setCellValueFactory(cellData-> cellData.getValue().fechaLlegadaProperty());
        // Clear person details.
        showPersonDetails(null);
        showReservaDetails(null);

        // Listen for selection changes and show the person details when changed.
        tablaPersona.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

        tablaReserva.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue, newValue) -> showReservaDetails(newValue));

        tablaPersona.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> personReserva(newValue.getDNI()));


        tablaPersona.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    i = tablaPersona.getSelectionModel().getSelectedIndex();
                    if (i != null) {
                        pasarIndex();
                    }
                });

        tablaReserva.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    iR = tablaReserva.getSelectionModel().getSelectedIndex();
                    if (iR != null) {
                        pasarIndexReserva();
                    }
                });

    }

    /**
     * Metodo para cargar las reservas de la persona
     * @param dni
     */
    private void personReserva(String dni){
        ObservableList<Reserva> reservaData = FXCollections.observableArrayList();
        reservaData.addAll(mainApp.addListReserva(dni));
        mainApp.setReservaData(reservaData);
        tablaReserva.setItems(mainApp.getReservaData());
    }

    /**
     * Muestra los detalles de la reserva
     * @param reserva
     */
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


    /**
     * Muestra los detalles de la persona
     * @param person
     */
    void showPersonDetails(Persona person) {
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

    /**
     * Pasa el index de la tabla de personas
     */
    public void pasarIndex(){
        mainApp.setI(i);
    }

    /**
     * Pasa el index de la tabla de reserva
     */
    public void pasarIndexReserva(){
        mainApp.setiR(iR);
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        tablaPersona.setItems(mainApp.getPersonData());
        mainApp.setTablaPersona(tablaPersona);
        mainApp.setTablaReserva(tablaReserva);
    }
}