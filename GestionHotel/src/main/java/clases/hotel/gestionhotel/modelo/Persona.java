package clases.hotel.gestionhotel.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private StringProperty DNI;
    private StringProperty nombre;
    private StringProperty apellidos;
    private IntegerProperty direccion;
    private StringProperty localidad;
    private StringProperty provincia;


    public Persona(String DNI, String nombre, String apellidos, Integer direccion, String localidad, String provincia) {
        this.DNI = new SimpleStringProperty(DNI);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleIntegerProperty(41000);
        this.localidad = new SimpleStringProperty("Sevilla");
        this.provincia = new SimpleStringProperty("Sevilla");
    }

    public Persona() {
    }

    public String getDNI() {
        return DNI.get();
    }

    public StringProperty DNIProperty() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI.set(DNI);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public int getDireccion() {
        return direccion.get();
    }

    public IntegerProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion.set(direccion);
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public String getProvincia() {
        return provincia.get();
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }
}
