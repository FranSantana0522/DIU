package clases.hotel.gestionhotel;

import clases.hotel.gestionhotel.modelo.ExceptionGH;
import clases.hotel.gestionhotel.modelo.PersonaVO;
import clases.hotel.gestionhotel.modelo.ReservaVO;
import clases.hotel.gestionhotel.modelo.repository.GestionRepository;
import clases.hotel.gestionhotel.modelo.repository.impl.GestionRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private static GestionRepository gr;

    public static void main(String[] args) throws ExceptionGH {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de hoteles");

        initRootLayout();

        showPersonOverview();
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
            //RootLayoutController controller = loader.getController();
            //controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GestionOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            //PersonOverviewController controller = loader.getController();
            //controller.setModelo(am);
            //controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
