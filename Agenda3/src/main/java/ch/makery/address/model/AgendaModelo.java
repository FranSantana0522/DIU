package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;

    DoubleProperty progreso=new SimpleDoubleProperty(0);

    public DoubleProperty setProgreso(Integer tamaño){
        this.progreso.set(tamaño.doubleValue());
        return progreso;
    }
    public AgendaModelo(){

    }
    public ArrayList<PersonVO> listarPersonas() throws ExcepcionPerson {
        return personRepository.ObtenerListaPersona();
    }
    public void crearPersonVO(PersonVO personVO) throws ExcepcionPerson {
        personRepository.addPerson(personVO);
    }
    public void editarPersonVO(PersonVO personVO) throws ExcepcionPerson {
        personRepository.editPerson(personVO);
    }
    public void deletePersonVO(PersonVO personVO) throws ExcepcionPerson {
        personRepository.deletePerson(personVO.getId());
    }
    public int lastId() throws ExcepcionPerson {
        return personRepository.lastId();
    }
    public void setImpl(PersonRepository inter) {
        this.personRepository = inter;
    }
}


