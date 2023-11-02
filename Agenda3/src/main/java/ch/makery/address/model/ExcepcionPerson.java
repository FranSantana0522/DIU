package ch.makery.address.model;

import ch.makery.address.MainApp;
import javafx.scene.control.Alert;

public class ExcepcionPerson extends Exception {
    private Alert mensajeA;
    private String mensaje;

    /**
     * Constructor vacio
     */
    public ExcepcionPerson() {
    }

    /**
     * Constructor que recibe un mensaje
     * @param ms
     */
    public ExcepcionPerson(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }

    public  ExcepcionPerson(Alert error){
        this.mensajeA=error;
    }
}
