package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;


    IntegerProperty numeroPersonas=new SimpleIntegerProperty();

    public void setNumeroPersonas(Integer nP){
        this.numeroPersonas.set(nP);
    }
    public  void decNumeroPersonas(){
        this.numeroPersonas.set(numeroPersonas.get()-1);
    }public  void incNumeroPersonas() {
        this.numeroPersonas.set(numeroPersonas.get()+1);
    }

    public IntegerProperty numeroPersonasProperty() {
        return numeroPersonas;
    }

    /**
     * Constructor vacio
     */
    public AgendaModelo(){

    }

    /**
     * Lista las personas de la base de datos a partir de la interfaz
     * @return lista de persona de la base de datos
     * @throws ExcepcionPerson
     */
    public ArrayList<PersonVO> listarPersonas() throws ExcepcionPerson {
        return personRepository.ObtenerListaPersona();
    }

    /**
     * Crea la persona en la base de datos a partir de la interfaz
     * @param personVO
     * @throws ExcepcionPerson
     */
    public void crearPersonVO(PersonVO personVO) throws ExcepcionPerson {
        personRepository.addPerson(personVO);
    }

    /**
     * Edita la persona en la base de daros a partir de la interfaz.
     * @param personVO
     * @throws ExcepcionPerson
     */
    public void editarPersonVO(PersonVO personVO) throws ExcepcionPerson {
        personRepository.editPerson(personVO);
    }

    /**
     * Borra la persona de la base de datos a partir de la interfaz
     * @param personVO
     * @throws ExcepcionPerson
     */
    public void deletePersonVO(PersonVO personVO) throws ExcepcionPerson {
        personRepository.deletePerson(personVO.getId());
    }

    /**
     * Obtiene el id de la ultima persona de la base de datos.
     * @return id
     * @throws ExcepcionPerson
     */
    public int lastId() throws ExcepcionPerson {
        return personRepository.lastId();
    }

    /**
     * Instanciamos el interfaz
     * @param inter
     */
    public void setImpl(PersonRepository inter) {
        this.personRepository = inter;
    }
}


