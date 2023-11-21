package clases.hotel.gestionhotel.modelo;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class Reserva {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> fechaLlegada;
    private ObjectProperty<LocalDate> fechaFin;
    private IntegerProperty numHabitacion;
    private StringProperty tipHab;
    private BooleanProperty fumador;
    private StringProperty regAloj;
    private StringProperty DNICliente;

    public Reserva(Integer id, LocalDate fechaLlegada, LocalDate fechaFin, Integer numHabitacion, String tipHab, Boolean fumador, String regAloj, String DNICliente) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaLlegada = new SimpleObjectProperty<>(fechaLlegada);
        this.fechaFin = new SimpleObjectProperty<>(fechaFin);
        this.numHabitacion = new SimpleIntegerProperty(numHabitacion);
        this.tipHab = new SimpleStringProperty(tipHab);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.regAloj = new SimpleStringProperty(regAloj);
        this.DNICliente = new SimpleStringProperty(DNICliente);
    }

    public Reserva() {
        this(0,null,null,0,null,false,null,null);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada.get();
    }

    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public LocalDate getFechaFin() {
        return fechaFin.get();
    }

    public ObjectProperty<LocalDate> fechaFinProperty() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin.set(fechaFin);
    }

    public int getNumHabitacion() {
        return numHabitacion.get();
    }

    public IntegerProperty numHabitacionProperty() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion.set(numHabitacion);
    }

    public String getTipHab() {
        return tipHab.get();
    }

    public StringProperty tipHabProperty() {
        return tipHab;
    }

    public void setTipHab(String tipHab) {
        this.tipHab.set(tipHab);
    }

    public boolean isFumador() {
        return fumador.get();
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }

    public String getRegAloj() {
        return regAloj.get();
    }

    public StringProperty regAlojProperty() {
        return regAloj;
    }

    public void setRegAloj(String regAloj) {
        this.regAloj.set(regAloj);
    }

    public String getDNICliente() {
        return DNICliente.get();
    }

    public StringProperty DNIClienteProperty() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente.set(DNICliente);
    }
}