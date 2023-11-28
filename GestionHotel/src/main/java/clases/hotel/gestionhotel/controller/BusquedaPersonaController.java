package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.modelo.*;
import clases.hotel.gestionhotel.util.Conversor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BusquedaPersonaController {

    @FXML
    private TextField DNIbusq;
    @FXML
    private TableView<Persona> tablaPersona;
    private ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Persona, String> dniC;
    @FXML
    private TableColumn<Persona, String> nombreC;
    @FXML
    private TableColumn<Persona, String> apellidosC;
    @FXML
    private TableColumn<Persona, Integer> direcionC;
    @FXML
    private TableColumn<Persona, String> localidadC;
    @FXML
    private TableColumn<Persona, String> provinciaC;
    @FXML
    private TableView<Reserva> tablaReserva;
    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reserva, Integer> idC;
    @FXML
    private TableColumn<Reserva, LocalDate> fechaEntradaC;
    @FXML
    private TableColumn<Reserva, LocalDate> fechaFinC;
    @FXML
    private TableColumn<Reserva, Integer> numHabC;
    @FXML
    private TableColumn<Reserva, String> tipHabC;
    @FXML
    private TableColumn<Reserva, Boolean> fumC;
    @FXML
    private TableColumn<Reserva, String> regAlojC;
    private Stage dialogStage;
    @FXML
    private Button lupa;
    private Conversor conv;
    private GestionModelo gm;

    public Conversor getConv() {
        return conv;
    }

    public void setConv(Conversor conv) {
        this.conv = conv;
    }

    public GestionModelo getGm() {
        return gm;
    }

    public void setGm(GestionModelo gm) {
        this.gm = gm;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Boton para cerrar
     * @param actionEvent
     */
    public void handleCerrar(ActionEvent actionEvent) {
        dialogStage.close();
    }

    /**
     * Boton para vaciar el campo dni
     * @param actionEvent
     */
    public void handleVaciar(ActionEvent actionEvent) {
        DNIbusq.setText("");
    }

    /**
     * Boton para buscar persona por dni
     *
     */
    public void handleBuscar() {
        try {
            PersonaVO personaVO=new PersonaVO();
            personaVO.setDNIVO(DNIbusq.getText());
            Persona persona=new Persona();
            persona=conv.convertirPersona(gm.busquedaPersonaDNI(personaVO));
            listaPersonas.clear();
            if (persona != null) {
                listaPersonas.add(persona);
            }
        }catch (ExceptionGH e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error con la lista de la persona");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos para buscar la persona o no existe la persona");
            alert.showAndWait();
        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al buscar la persona");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos para buscar la persona o no existe la persona");
            alert.showAndWait();
        }
        dniC.setCellValueFactory(cellData -> cellData.getValue().DNIProperty());
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidosC.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        direcionC.setCellValueFactory(cellData -> cellData.getValue().direccionProperty().asObject());
        localidadC.setCellValueFactory(cellData -> cellData.getValue().localidadProperty());
        provinciaC.setCellValueFactory(cellData -> cellData.getValue().provinciaProperty());
        tablaPersona.setItems(listaPersonas);
        try {
            ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
            ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
            listaReservaVO = gm.listarReservas(DNIbusq.getText());
            listaReserva=conv.listaReserva(listaReservaVO);
            listaReservas.clear();
            listaReservas.addAll(listaReserva);
        }catch (ExceptionGH e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al buscar las reservas");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos para buscar las reservas");
            alert.showAndWait();
        }
        idC.setCellValueFactory(cellData-> cellData.getValue().idProperty().asObject());
        fechaEntradaC.setCellValueFactory(cellData-> cellData.getValue().fechaLlegadaProperty());
        fechaFinC.setCellValueFactory(cellData-> cellData.getValue().fechaFinProperty());
        numHabC.setCellValueFactory(cellData-> cellData.getValue().numHabitacionProperty().asObject());
        tipHabC.setCellValueFactory(cellData-> cellData.getValue().tipHabProperty());
        fumC.setCellValueFactory(cellData-> cellData.getValue().fumadorProperty());
        regAlojC.setCellValueFactory(cellData-> cellData.getValue().regAlojProperty());
        tablaReserva.setItems(listaReservas);
    }

    @FXML
    public void initialize() {
        DNIbusq.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleBuscar();
                }
            }
        });
    }



}
