package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.modelo.GestionModelo;
import clases.hotel.gestionhotel.modelo.Persona;
import clases.hotel.gestionhotel.modelo.Reserva;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CrearReservaController {
    @FXML
    private Label dniR;
    @FXML
    private Label nombreR;
    @FXML
    private Label apellidosR;
    @FXML
    private Label direccionR;
    @FXML
    private Label localidadR;
    @FXML
    private Label provinciaR;
    private Persona persona;

    private Stage dialogStage;

    @FXML
    private DatePicker fechaL;
    @FXML
    private DatePicker fechaS;
    @FXML
    private Spinner<Integer> numHab;
    @FXML
    private ChoiceBox<String> tipHab;
    @FXML
    private RadioButton alojYdes;
    @FXML
    private RadioButton mediaP;
    @FXML
    private RadioButton pensionC;
    @FXML
    private CheckBox fum;
    @FXML
    private Label fumText;
    Persona person;
    Reserva reserva;

    private String DNI_Cliente;

    private GestionModelo gm;
    private  boolean okClicked=false;

    public void setGm(GestionModelo gm) {
        this.gm = gm;
    }

    /**
     * Se ejecuta al iniciar el controlador
     */
    @FXML
    private void initialize() {
        if(persona==null){
            dniR.setText("");
            nombreR.setText("");
            apellidosR.setText("");
            direccionR.setText("");
            localidadR.setText("");
            provinciaR.setText("");
        }
        fum.selectedProperty().addListener((observable, oldValue, newValue)->{
            if(newValue){
                fumText.setVisible(true);
            }else{
                fumText.setVisible(false);
            }
        });
        tipHab.getItems().addAll("Doble de uso individual", "Doble", "Junior suite", "Suite");

        Platform.runLater(() -> {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1, 1);
            numHab.setValueFactory(valueFactory);
            numHab.getValueFactory().setValue(1);
        });
    }

    /**
     * Hago un set de la persona
     * @param person
     */
    public void setPerson(Persona person) {
        this.person = person;
        dniR.setText(person.getDNI());
        nombreR.setText(person.getNombre());
        apellidosR.setText(person.getApellidos());
        direccionR.setText(Integer.toString(person.getDireccion()));
        localidadR.setText(person.getLocalidad());
        provinciaR.setText(person.getProvincia());
    }

    /**
     * Hago un set de la reserva
     * @param reserva
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        fechaL.setValue(reserva.getFechaLlegada());
        fechaS.setValue(reserva.getFechaFin());
        numHab.getEditor().setText(String.valueOf(reserva.getNumHabitacion()));
        tipHab.getSelectionModel().select(reserva.getTipHab());
        if(reserva.getRegAloj()!=null){
            if(reserva.getRegAloj().equals(alojYdes.getText())){
                alojYdes.setSelected(true);
            }
            if(reserva.getRegAloj().equals(mediaP.getText())){
                mediaP.setSelected(true);
            }
            if(reserva.getRegAloj().equals(pensionC.getText())){
                pensionC.setSelected(true);
            }
        }
        Boolean isFumador=reserva.isFumador();
        if(isFumador!=null){
            if(reserva.isFumador()){
                fum.setSelected(true);
            }
        }
        DNI_Cliente=dniR.getText();
    }

    /**
     * Boton de vaciar los campos
     * @param actionEvent
     */
    public void handleVaciar(ActionEvent actionEvent) {
        fechaL.getEditor().clear();
        fechaS.getEditor().clear();
        tipHab.getSelectionModel().clearSelection();
        alojYdes.setSelected(false);
        mediaP.setSelected(false);
        pensionC.setSelected(false);
        fum.setSelected(false);
    }

    /**
     * Boton para aceptar
     * @param actionEvent
     */
    public void handleAceptar(ActionEvent actionEvent) {
        if (isInputValid()) {
            reserva.setFechaLlegada(fechaL.getValue());
            reserva.setFechaFin(fechaS.getValue());
            reserva.setTipHab(tipHab.getValue());
            reserva.setNumHabitacion(Integer.parseInt(numHab.getEditor().getText()));
            if(alojYdes.isSelected()){
                reserva.setRegAloj(alojYdes.getText());
            }
            if(mediaP.isSelected()){
                reserva.setRegAloj(mediaP.getText());
            }
            if(pensionC.isSelected()){
                reserva.setRegAloj(pensionC.getText());
            }
            reserva.setFumador(fum.isSelected());
            reserva.setDNICliente(DNI_Cliente);
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Boton para cancelar
     * @param actionEvent
     */
    public void handleCancelar(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    /**
     * Devuelve okClicked
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Comprueba que los datos esten bien introducidos
     * @return
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (fechaL.getValue() == null ||fechaL.getValue().isBefore(LocalDate.now())) {
            errorMessage += "Fecha llegada incorrecto\n";
        }else {
            String fechaIngresada = fechaL.getValue().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate fecha = LocalDate.parse(fechaIngresada, formatter);
            } catch (DateTimeParseException e) {
                errorMessage += "Fecha llegada formato incorrecto. Usa el formato yyyy-MM-dd\n";
            }
        }
        if (fechaS.getValue() == null ||fechaS.getValue().isBefore(fechaL.getValue())) {
            errorMessage += "Fecha fin incorrecta\n";
        }else {
            String fechaIngresada = fechaS.getValue().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate fecha = LocalDate.parse(fechaIngresada, formatter);
            } catch (DateTimeParseException e) {
                errorMessage += "Fecha Fin formato incorrecto. Usa el formato yyyy-MM-dd\n";
            }
        }
        if (numHab.getValue() == null || numHab.getValue() == 0) {
            errorMessage += "Numero de habitacion no valido\n";
        }else {
            try {
                Integer.parseInt(numHab.getValue().toString());
            } catch (NumberFormatException e) {
                errorMessage += "El numero de habitacion debe ser un numero\n";
            }
        }

        if (tipHab.getValue() == null || tipHab.getValue().length() == 0) {
            errorMessage += "Tipo de habitacion no valido\n";
        }
        if(!alojYdes.isSelected() && !mediaP.isSelected() && !pensionC.isSelected()){
            errorMessage += "Regimen de alojamiento no valido\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Campos invalidos");
            alerta.setHeaderText("Por favor introduzca los campos correctamente");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();
            return false;
        }
    }

}
