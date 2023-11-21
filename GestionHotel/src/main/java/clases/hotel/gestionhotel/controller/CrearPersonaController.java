package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.modelo.GestionModelo;
import clases.hotel.gestionhotel.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class CrearPersonaController {
    @FXML
    private TextField dniField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField direcionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;
    private Stage dialogStage;
    private GestionModelo gm;
    private Persona person;
    private boolean okClicked = false;


    public void setPerson(Persona person) {
        this.person = person;
        dniField.setText(person.getDNI());
        nombreField.setText(person.getNombre());
        apellidosField.setText(person.getApellidos());
        direcionField.setText(Integer.toString(person.getDireccion()));
        localidadField.setText(person.getLocalidad());
        provinciaField.setText(person.getProvincia());
    }
    public void setGm(GestionModelo gm) {
        this.gm = gm;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void handleOk(ActionEvent actionEvent) {
        if (isInputValid()) {
            person.setDNI(dniField.getText());
            person.setNombre(nombreField.getText());
            person.setApellidos(apellidosField.getText());
            person.setDireccion(Integer.parseInt(direcionField.getText()));
            person.setLocalidad(localidadField.getText());
            person.setProvincia(provinciaField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (dniField.getText() == null ||dniField.getText().length()==0 || dniField.getText().length() != 9) {
            errorMessage += "DNI incorrecto\n";
        }
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre no valido\n";
        }
        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMessage += "Apellidos no validos\n";
        }

        if (direcionField.getText() == null || direcionField.getText().length() == 0 ||
                direcionField.getText().length()!=5) {
            errorMessage += "Direccion incorrecta\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(direcionField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "La direccion debe ser un numero\n";
            }
        }

        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "Localidad no valida\n";
        }

        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "Provincia no valida\n";
        }
//        } else {
//            if (!DateUtil.validDate(birthdayField.getText())) {
//                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
//            }
//        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Invalid Fields");
            alerta.setHeaderText("Please correct invalid fields");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();
            return false;
        }
    }
}