module com.example.gestionhotel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens clases.hotel.gestionhotel to javafx.fxml;
    exports clases.hotel.gestionhotel;
    opens clases.hotel.gestionhotel.controller to javafx.fxml;
    exports clases.hotel.gestionhotel.controller;
}