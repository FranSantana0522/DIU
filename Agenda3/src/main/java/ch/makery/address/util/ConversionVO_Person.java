package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class ConversionVO_Person {

    public Person convertirPersona(PersonVO personVO){
        Person person = new Person();
        person.setIdentificador(personVO.getId());
        person.setFirstName(personVO.getNombre());
        person.setLastName(personVO.getApellido());
        person.setStreet(personVO.getCalle());
        person.setPostalCode(personVO.getCodigoPostal());
        person.setCity(personVO.getCiudad());
        person.setBirthday(personVO.getNacimiento());
        return person;
    }

    public ArrayList<Person> lista(ArrayList<PersonVO> listaPersonVO){
        ArrayList<Person> listaPerson = new ArrayList<Person>();
        Person person = new Person();
        for(int i=0;i<listaPersonVO.size();i++){
            person=convertirPersona(listaPersonVO.get(i));
            listaPerson.add(i,person);
        }
        return listaPerson;
    }
    public PersonVO convertirPersonaVO(Person person){
        PersonVO personVO = new PersonVO();
        personVO.setId(person.getIdentificador());
        personVO.setNombre(person.getFirstName());
        personVO.setApellido(person.getLastName());
        personVO.setCalle(person.getStreet());
        personVO.setCodigoPostal(person.getPostalCode());
        personVO.setCiudad(person.getCity());
        personVO.setNacimiento(person.getBirthday());
        return personVO;
    }

    public ArrayList<PersonVO> listaPerson(ArrayList<Person> listaPerson){
        ArrayList<PersonVO> listaPersonVO = new ArrayList<PersonVO>();
        PersonVO personVO = new PersonVO();
        for(int i=0;i<listaPerson.size();i++){
            personVO=convertirPersonaVO(listaPerson.get(i));
            listaPersonVO.add(i,personVO);
        }
        return listaPersonVO;
    }
}
