package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.MainApp;
import clases.hotel.gestionhotel.modelo.Persona;
import javafx.event.ActionEvent;

public class RootLayoutController {

    private MainApp mainApp;






    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }

    public void handleAddPersona(ActionEvent actionEvent) {
        Persona tempPerson = new Persona();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
    }

    public void handleEditPersona(ActionEvent actionEvent) {
    }

    public void handleDeletePersona(ActionEvent actionEvent) {
    }

    public void handleAddReserva(ActionEvent actionEvent) {
    }

    public void handleEditReserva(ActionEvent actionEvent) {
    }

    public void handleDeleteReserva(ActionEvent actionEvent) {
    }

    public void handleAplication(ActionEvent actionEvent) {
    }

    public void handleOcupation(ActionEvent actionEvent) {
    }
}
