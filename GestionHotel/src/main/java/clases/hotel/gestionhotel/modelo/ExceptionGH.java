package clases.hotel.gestionhotel.modelo;

import javafx.scene.control.Alert;

public class ExceptionGH extends Exception{
    private Alert mensajeA;
    private String mensaje;

    public ExceptionGH() {

    }
    public ExceptionGH(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }

    public  ExceptionGH(Alert error){
        this.mensajeA=error;
    }
}