module com.example.ex1ev1diu2023 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires Modelo;

    opens com.example.ex1ev1diu2023 to javafx.fxml;
    exports com.example.ex1ev1diu2023;
    opens com.example.ex1ev1diu2023.controller to javafx.fxml;
    exports com.example.ex1ev1diu2023.controller;
}