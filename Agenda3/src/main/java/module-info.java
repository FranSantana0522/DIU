module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens ch.makery.address to javafx.fxml;
    exports ch.makery.address;
    opens ch.makery.address.controller to javafx.fxml;
    exports ch.makery.address.controller;
    opens ch.makery.address.model to javafx.fxml;
    exports ch.makery.address.model;
    opens ch.makery.address.util to javafx.fxml;
    exports ch.makery.address.util;
}