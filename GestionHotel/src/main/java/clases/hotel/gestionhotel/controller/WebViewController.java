package clases.hotel.gestionhotel.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.File;

public class WebViewController {
    private Stage dialogStage;
    @FXML
    private WebView webView;


    @FXML
    private void initialize() {
        WebEngine webEngine = webView.getEngine();

        String indexPath = "C:\\Users\\FRANCISCO JAVIER\\Documents\\DIU\\GestionHotel\\src\\main\\java\\clases\\hotel\\gestionhotel\\javaDoc\\index.html";

        File indexFile = new File(indexPath);
        if (indexFile.exists()) {
            String url = indexFile.toURI().toString();
            webEngine.load(url);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al mostrar la documentacion.");
            alert.setTitle("Error con la ruta especificada");
            alert.setContentText("No se mostrar la documentacion del javaDoc");
            alert.showAndWait();
        }
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
