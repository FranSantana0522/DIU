package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.modelo.GestionModelo;
import clases.hotel.gestionhotel.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CrearPersonaController {
    private Stage dialogStage;
    private GestionModelo gm;
    private Persona person;
    private boolean okClicked = false;


    public void setPerson(Persona person) {
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }
    public void setGm(GestionModelo gm) {
        this.gm = gm;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void handleOk(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
    }
    public boolean isOkClicked() {
        return okClicked;
    }

}
