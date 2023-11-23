package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.MainApp;
import clases.hotel.gestionhotel.modelo.*;
import clases.hotel.gestionhotel.util.Conversor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class RootLayoutController {

    private MainApp mainApp;
    private GestionModelo gm;
    private Conversor conv;
    private TableView<Persona> tablaPersona;

    private TableView<Reserva> tablaReserva;





    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }

    public void setGm(GestionModelo gm) {
        this.gm = gm;
    }

    public void setConv(Conversor conv) {
        this.conv = conv;
    }

    public void handleAddPersona(ActionEvent actionEvent) {
        Persona tempPerson = new Persona();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            try{
                PersonaVO personVO=new PersonaVO();
                personVO=conv.convertirPersonaVO(tempPerson);
                gm.crearPersonaVO(personVO);
                mainApp.getPersonData().add(tempPerson);
            }catch(ExceptionGH e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir la persona");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para añadir la persona");
                alert.showAndWait();
            }
        }
    }

    public void handleEditPersona(ActionEvent actionEvent) {
        tablaPersona=mainApp.getTablaPersona();
        Persona selectedPerson = tablaPersona.getItems().get(mainApp.getI());
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                try{
                    PersonaVO personVO=new PersonaVO();
                    personVO=conv.convertirPersonaVO(selectedPerson);
                    gm.editarPersonaVO(personVO);
                }catch(ExceptionGH e){
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al editar la persona");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para editar la persona");
                    alert.showAndWait();
                }
            }

        } else {
            // Nothing selected.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No Selection");
            alerta.setHeaderText("No Person Selected");
            alerta.setContentText("Please select a person in the table.");
            alerta.showAndWait();
        }
    }

    public void handleDeletePersona(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("No disponible");
        alerta.setHeaderText("Esta opcion no esta disponible");
        alerta.setContentText("El borrado de personas no esta disponible en este momento.");
        alerta.showAndWait();
    }

    public void handleAddReserva(ActionEvent actionEvent) {
        tablaPersona=mainApp.getTablaPersona();
        Persona selectedPerson = tablaPersona.getItems().get(mainApp.getI());
        Reserva tempReserva = new Reserva();
        boolean okClicked = mainApp.showReservaEditDialog(tempReserva, selectedPerson);
        if (okClicked) {
            try{
                tempReserva.setId(gm.lastIdReserva()+1);
                ReservaVO reservaVO=new ReservaVO();
                reservaVO=conv.convertirReservaVO(tempReserva);
                gm.crearReservaVO(reservaVO);
                mainApp.getReservaData().add(tempReserva);
            }catch(ExceptionGH e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir la reserva");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para añadir la reserva");
                alert.showAndWait();
            }
        }
    }

    public void handleEditReserva(ActionEvent actionEvent) {
        tablaPersona=mainApp.getTablaPersona();
        Persona selectedPerson = tablaPersona.getItems().get(mainApp.getI());
        tablaReserva=mainApp.getTablaReserva();
        Reserva selectedReserva =tablaReserva.getItems().get(mainApp.getiR());
        boolean okClicked = mainApp.showReservaEditDialog(selectedReserva, selectedPerson);
        if (okClicked) {
            try{
                ReservaVO reservaVO=new ReservaVO();
                reservaVO=conv.convertirReservaVO(selectedReserva);
                gm.editarReservaVO(reservaVO);
            }catch(ExceptionGH e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir la reserva");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para añadir la reserva");
                alert.showAndWait();
            }
        }
    }

    public void handleDeleteReserva(ActionEvent actionEvent) {
        int selectedIndex = mainApp.getiR();
        if (selectedIndex >= 0) {
            try {
                gm.deleteReservaVO(conv.convertirReservaVO((tablaReserva.getItems().get(selectedIndex))));
                tablaReserva.getItems().remove(selectedIndex);
            }catch (ExceptionGH e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al eliminar la persona");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para eliminar la persona");
                alert.showAndWait();
            }
        } else {
            // Nothing selected.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No Selection");
            alerta.setHeaderText("No Person Selected");
            alerta.setContentText("Please select a person in the table.");
            alerta.showAndWait();
        }
    }

    public void handleAplication(ActionEvent actionEvent) {
    }

    public void handleOcupation(ActionEvent actionEvent) {
    }

    public void handleBusqueda(ActionEvent actionEvent) {
        mainApp.showBusquedaPersona();
    }
}