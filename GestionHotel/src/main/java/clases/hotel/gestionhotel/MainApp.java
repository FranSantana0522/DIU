package clases.hotel.gestionhotel;

import clases.hotel.gestionhotel.controller.CrearPersonaController;
import clases.hotel.gestionhotel.controller.CrearReservaController;
import clases.hotel.gestionhotel.controller.GestionOverviewController;
import clases.hotel.gestionhotel.controller.RootLayoutController;
import clases.hotel.gestionhotel.modelo.*;
import clases.hotel.gestionhotel.modelo.repository.GestionRepository;
import clases.hotel.gestionhotel.modelo.repository.impl.GestionRepositoryImpl;
import clases.hotel.gestionhotel.util.Conversor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private GestionModelo gm;
    private Conversor conv;

    private static GestionRepository gr;

    private ObservableList<Persona> personData = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();

    private TableView<Persona> tablaPersona;
    private Integer i;


    public MainApp(){
        personData.addAll(addListPersona());
    }

    public ArrayList<Persona> addListPersona(){
        gm=new GestionModelo();
        conv=new Conversor();
        gr = new GestionRepositoryImpl();
        gm.setImpl(gr);
        ArrayList<PersonaVO>listaPersonaVO = new ArrayList<PersonaVO>();
        ArrayList<Persona>listaPersona = new ArrayList<Persona>();
        try{
            listaPersonaVO =gm.listarPersonas();
        } catch (ExceptionGH e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaPersona=conv.listaPersona(listaPersonaVO);
        return listaPersona;
    }
    public ArrayList<Reserva> addListReserva(String dniC){
        gm=new GestionModelo();
        conv=new Conversor();
        gr = new GestionRepositoryImpl();
        gm.setImpl(gr);
        ArrayList<ReservaVO>listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
        try{
            listaReservaVO = gm.listarReservas(dniC);
        } catch (ExceptionGH e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Reservas");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva=conv.listaReserva(listaReservaVO);
        return listaReserva;
    }
    public ObservableList<Persona> getPersonData() {
        return personData;
    }

    public void setPersonData(ObservableList<Persona> personData) {
        this.personData = personData;
    }

    public ObservableList<Reserva> getReservaData() {
        return reservaData;
    }

    public void setReservaData(ObservableList<Reserva> reservaData) {
        this.reservaData = reservaData;
    }

    public static void main(String[] args) throws ExceptionGH {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de hoteles");

        initRootLayout();

        showGestionOverview();
    }
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            // FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RootLayout.fxml"));
            //  FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
            // loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootLayoutController controller = loader.getController();
            controller.setGm(gm);
            controller.setConv(conv);
            GestionOverviewController goc=new GestionOverviewController();
            controller.setGoc(goc);
            controller.setTablaPersona(tablaPersona);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public void setTablaPersona(TableView<Persona> tablaPersona) {
        this.tablaPersona = tablaPersona;
    }

    public void showGestionOverview() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GestionOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            GestionOverviewController controller = loader.getController();
            //controller.setModelo(am);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showPersonEditDialog(Persona person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CrearPersona.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Crear/Editar Persona");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CrearPersonaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            RootLayoutController rlc=new RootLayoutController();
            rlc.setI(i);
            controller.setGm(gm);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showReservaEditDialog(Reserva reserva) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CrearReserva.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Crear/Editar Reserva");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CrearReservaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            RootLayoutController rlc=new RootLayoutController();
            rlc.setI(i);
          //  controller.setGm(gm);
          //  controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}