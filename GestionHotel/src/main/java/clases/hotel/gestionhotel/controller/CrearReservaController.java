package clases.hotel.gestionhotel.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    private  boolean okClicked=false;

    @FXML
    private void initialize() {
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


    public void handleVaciar(ActionEvent actionEvent) {
        fechaL.getEditor().clear();
        fechaS.getEditor().clear();
        tipHab.getSelectionModel().clearSelection();
        alojYdes.setSelected(false);
        mediaP.setSelected(false);
        pensionC.setSelected(false);
        fum.setSelected(false);
    }

    public void handleAceptar(ActionEvent actionEvent) {

    }

    public void handleCancelar(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}
