package clases.hotel.gestionhotel;

import clases.hotel.gestionhotel.modelo.ExceptionGH;
import clases.hotel.gestionhotel.modelo.PersonaVO;
import clases.hotel.gestionhotel.modelo.ReservaVO;
import clases.hotel.gestionhotel.modelo.repository.GestionRepository;
import clases.hotel.gestionhotel.modelo.repository.impl.GestionRepositoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class MainApp extends Application {

    private static GestionRepository gr;

    public static void main(String[] args) throws ExceptionGH {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
