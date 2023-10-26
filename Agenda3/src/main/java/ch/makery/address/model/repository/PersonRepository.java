package ch.makery.address.model.repository;


import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> ObtenerListaPersona() throws ExcepcionPerson;

    void addPerson(PersonVO var1) throws ExcepcionPerson;

    void deletePerson(Integer var1) throws ExcepcionPerson;

    void editPerson(PersonVO var1) throws ExcepcionPerson;

    int lastId() throws ExcepcionPerson;
}
