package ch.makery.address.model;

import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import java.util.ArrayList;

public class AgendaModelo {
    PersonRepositoryImpl impl;

    public AgendaModelo(){

    }
    public ArrayList<PersonVO> listarPersonas() throws ExcepcionPerson {
        return impl.ObtenerListaPersona();
    }
    public void setImpl(PersonRepositoryImpl impl) {
        this.impl = impl;
    }
}


