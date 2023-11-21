package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.MainApp;
import clases.hotel.gestionhotel.modelo.ExceptionGH;
import clases.hotel.gestionhotel.modelo.GestionModelo;
import clases.hotel.gestionhotel.modelo.Persona;
import clases.hotel.gestionhotel.modelo.PersonaVO;
import clases.hotel.gestionhotel.util.Conversor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class RootLayoutController {

    private MainApp mainApp;
    private GestionModelo gm;
    private Conversor conv;
    private GestionOverviewController goc;
    private TableView<Persona> tablaPersona;
    private int i;





    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }

    public void setGm(GestionModelo gm) {
        this.gm = gm;
    }

    public void setConv(Conversor conv) {
        this.conv = conv;
    }

    public void setGoc(GestionOverviewController goc) {
        this.goc = goc;
    }

    public void setTablaPersona(TableView<Persona> tablaPersona) {
        this.tablaPersona = tablaPersona;
    }

    public void setI(int i) {
        this.i = i;
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
        Persona selectedPerson = tablaPersona.getItems().get(i);
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                try{
                    PersonaVO personVO=new PersonaVO();
                    personVO=conv.convertirPersonaVO(selectedPerson);
                    gm.editarPersonaVO(personVO);
                    goc.showPersonDetails(selectedPerson);
                }catch(ExceptionGH e){
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

    public void handleBusqueda(ActionEvent actionEvent) {
    }
}