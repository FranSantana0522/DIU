package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.modelo.GestionModelo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class OcupacionHabController {
    @FXML
    private ProgressIndicator progressDI;
    @FXML
    private ProgressIndicator progressD;
    @FXML
    private ProgressIndicator progressJS;
    @FXML
    private ProgressIndicator progressS;

    private IntegerProperty ocupDI=new SimpleIntegerProperty();
    private IntegerProperty ocupD=new SimpleIntegerProperty();
    private IntegerProperty ocupJS=new SimpleIntegerProperty();
    private IntegerProperty ocupS=new SimpleIntegerProperty();

    private GestionModelo gestionModelo;

    public void setGestionModelo(GestionModelo gestionModelo) {
        this.gestionModelo = gestionModelo;
    }
    public void setProgress(){
        this.ocupDI.bind(gestionModelo.getNumeroReservasProperty());
        this.progressDI.setProgress((double) ocupDI.get() /20);
        ocupDI.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressDI.setProgress((double) ocupDI.get() /20);
            }
        });
        this.ocupD.bind(gestionModelo.getNumeroReservasProperty2());
        this.progressD.setProgress((double) ocupD.get() /80);
        ocupD.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressD.setProgress((double) ocupD.get() /80);
            }
        });
        this.ocupJS.bind(gestionModelo.getNumeroReservasProperty3());
        this.progressJS.setProgress((double) ocupJS.get() /15);
        ocupJS.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressJS.setProgress((double) ocupJS.get() /15);
            }
        });
        this.ocupS.bind(gestionModelo.getNumeroReservasProperty4());
        this.progressS.setProgress((double) ocupS.get() /5);
        ocupS.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressS.setProgress((double) ocupS.get() /5);
            }
        });
    }
    @FXML
    private void initialize() {
    }
}
