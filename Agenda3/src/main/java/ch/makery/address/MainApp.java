package ch.makery.address;

import ch.makery.address.controller.BirthdayStatisticsController;
import ch.makery.address.controller.PersonEditDialogController;
import ch.makery.address.controller.PersonOverviewController;
import ch.makery.address.controller.RootLayoutController;
import ch.makery.address.model.AgendaModelo;
import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.ConversionVO_Person;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;
    private AgendaModelo am;
    private ConversionVO_Person cvp;
    private PersonRepositoryImpl impl;

    private ObservableList<Person> personData = FXCollections.observableArrayList();
    Double numPerson;
    public MainApp() {
        // Add some sample data
        personData.addAll(addList());
    }
    public ArrayList<Person> addList(){
        am=new AgendaModelo();
        cvp=new ConversionVO_Person();
        impl = new PersonRepositoryImpl();
        am.setImpl(impl);
        ArrayList<PersonVO>listaPersonVO = new ArrayList<PersonVO>();
        ArrayList<Person>listaPerson = new ArrayList<Person>();
        try{
            listaPersonVO = am.listarPersonas();
        } catch (ExcepcionPerson e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaPerson=cvp.lista(listaPersonVO);
        return listaPerson;
    }
    public ObservableList<Person> getPersonData(){
        return personData;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agenda App");

        this.primaryStage.getIcons().add(new Image("file:resources/images/6612551.png"));

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
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
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setModelo(am);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionPerson e) {
            throw new RuntimeException(e);
        }
    }
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            numPerson= (double) personData.size();
            DoubleProperty n=new SimpleDoubleProperty(numPerson);
            controller.setPerson(person);
            controller.setAm(am);
            controller.setBarrita(am.setProgreso(n));
            IntegerProperty n2=new SimpleIntegerProperty(numPerson.intValue());
            controller.setProgreso(n2);
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
//        try {
//            PersonRepositoryImpl pri=new PersonRepositoryImpl();
//            PersonVO p=new PersonVO("Paco","a","C/",41,"Sevilla", LocalDate.of(2000,10,10));
//            pri.addPerson(p);
//            PersonVO p2=new PersonVO("Paco2","a2","C/2",412,"Sevilla", LocalDate.of(2001,10,10));
//            PersonVO p3=new PersonVO("Paco3","a3","C/3",413,"Sevilla", LocalDate.of(2002,10,10));
//            pri.addPerson(p2);
//            pri.addPerson(p3);
//            pri.editPerson(p3,p2.getId());
//            pri.deletePerson(pri.lastId());
//            System.out.println(pri.lastId());
//            for (PersonVO persona: pri.ObtenerListaPersona()){
//                System.out.println(p.getId());
//            }
//        }catch (ExcepcionPerson e) {
//            throw new RuntimeException(e);
//        }
        launch(args);
    }
}