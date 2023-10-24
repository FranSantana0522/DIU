package ch.makery.address.model;

import java.time.LocalDate;

public class PersonVO {
        Integer id;
        String nombre;
        String apellido;
        String calle;
        Integer codigoPostal;
        String ciudad;
        LocalDate nacimiento;

    public PersonVO( String nombre, String apellido, String calle, Integer codigoPostal, String ciudad, LocalDate nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.nacimiento = nacimiento;
    }

    public PersonVO() {

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCalle() {
        return calle;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", calle='" + calle + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", nacimiento=" + nacimiento +
                '}';
    }
}
