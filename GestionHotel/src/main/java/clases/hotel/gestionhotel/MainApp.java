package clases.hotel.gestionhotel;

import clases.hotel.gestionhotel.controller.*;
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
    private TableView<Reserva> tablaReserva;
    private Integer i;
    private Integer iR;


    /**
     * Constructor de la clase donde añado la lista de personas
     */
    public MainApp() throws ExceptionGH {
        personData.addAll(addListPersona());
        gm.setNumeroReservas(listarDI().size());
        gm.setNumeroReservas2(listarD().size());
        gm.setNumeroReservas3(listarJS().size());
        gm.setNumeroReservas4(listarS().size());
    }

    /**
     * Añade a la tabla de personas las personas
     * @return
     */
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

    /**
     * Añade a la tabla reservas las reservas del cliente
     * @param dniC
     * @return
     */
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


    /**
     * Metodo Start
     * @param primaryStage Escenario principal
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de hoteles");

        initRootLayout();

        showGestionOverview();
    }

    /**
     * Inicio del root layout
     */
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
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter de i de la tabla persona
     * @param i
     */
    public void setI(Integer i) {
        this.i = i;
    }

    /**
     * Setter de la tabla de persona
     * @param tablaPersona
     */
    public void setTablaPersona(TableView<Persona> tablaPersona) {
        this.tablaPersona = tablaPersona;
    }

    /**
     * Getter de tabla persona
     * @return
     */
    public TableView<Persona> getTablaPersona() {
        return tablaPersona;
    }

    /**
     * Getter de i de la persona
     * @return
     */
    public Integer getI() {
        return i;
    }

    /**
     * Getter de  la tabla de reserva
     * @return
     */
    public TableView<Reserva> getTablaReserva() {
        return tablaReserva;
    }

    /**
     * Setter tabla de reserva
     * @param tablaReserva
     */
    public void setTablaReserva(TableView<Reserva> tablaReserva) {
        this.tablaReserva = tablaReserva;
    }

    /**
     * Getter de la i de la tabla reserva
     * @return
     */
    public Integer getiR() {
        return iR;
    }

    /**
     * Setter de la i de la tabla de reserva
     * @param iR
     */
    public void setiR(Integer iR) {
        this.iR = iR;
    }


    /**
     * Enseña el fxml de gestion
     */
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
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enseña el crear editar persona
     * @param person
     * @return
     */
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

    /**
     * Enseña la reserva para crear y editar
     * @param reserva
     * @param persona
     * @return
     */
    public boolean showReservaEditDialog(Reserva reserva, Persona persona) {
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
            controller.setGm(gm);
            controller.setPerson(persona);
            controller.setReserva(reserva);
            controller.setDialogStage(dialogStage);



            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *Enseña la busqueda de la persona
     */
    public void showBusquedaPersona() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BusquedaPersona.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Buscar persona");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            BusquedaPersonaController controller = loader.getController();
            controller.setGm(gm);
            controller.setConv(conv);
            controller.setDialogStage(dialogStage);



            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showWebView() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("JavaDoc.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Web View");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            WebViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showOcupacionTotal() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PorcentajeDeOcupacionTotal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ocupacion total");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            OcupacionTotalController controller = loader.getController();
            controller.setReservaData(listarTodo());

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExceptionGH e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Reserva> listarTodo() throws ExceptionGH {
        ArrayList<ReservaVO>listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
        try{
            listaReservaVO = gm.listarReservasTodas();
        } catch (ExceptionGH e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva=conv.listaReserva(listaReservaVO);
        return listaReserva;
    }
    public void showOcupacionHab() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("OcupacionHab.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ocupacion por habitacion");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            OcupacionHabController controller = loader.getController();
            controller.setGestionModelo(gm);

            controller.setProgress();
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Reserva> listarDI() throws ExceptionGH {
        ArrayList<ReservaVO>listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva>listaReservaFiltrada = new ArrayList<Reserva>();
        try{
            listaReservaVO = gm.listarReservasTodas();
        } catch (ExceptionGH e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva=conv.listaReserva(listaReservaVO);
        for(int indi=0;indi<listaReserva.size();indi++){
            if(listaReserva.get(indi).getTipHab().equals("Doble de uso individual")){
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy ) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }
    public ArrayList<Reserva> listarD() throws ExceptionGH {
        ArrayList<ReservaVO>listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva>listaReservaFiltrada = new ArrayList<Reserva>();
        try{
            listaReservaVO = gm.listarReservasTodas();
        } catch (ExceptionGH e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva=conv.listaReserva(listaReservaVO);
        for(int indi=0;indi<listaReserva.size();indi++){
            if(listaReserva.get(indi).getTipHab().equals("Doble")){
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);;
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy ) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }
    public ArrayList<Reserva> listarJS() throws ExceptionGH {
        ArrayList<ReservaVO>listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva>listaReservaFiltrada = new ArrayList<Reserva>();
        try{
            listaReservaVO = gm.listarReservasTodas();
        } catch (ExceptionGH e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva=conv.listaReserva(listaReservaVO);
        for(int indi=0;indi<listaReserva.size();indi++){
            if(listaReserva.get(indi).getTipHab().equals("Junior suite")){
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);;
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy ) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }
    public ArrayList<Reserva> listarS() throws ExceptionGH {
        ArrayList<ReservaVO>listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva>listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva>listaReservaFiltrada = new ArrayList<Reserva>();
        try{
            listaReservaVO = gm.listarReservasTodas();
        } catch (ExceptionGH e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva=conv.listaReserva(listaReservaVO);
        for(int indi=0;indi<listaReserva.size();indi++){
            if(listaReserva.get(indi).getTipHab().equals("Suite")){
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy ) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }
}