package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;

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
    public void setImpl(PersonRepository inter) {
        this.personRepository = inter;
    }
}


