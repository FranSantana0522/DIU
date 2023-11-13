package clases.hotel.gestionhotel.util;

import clases.hotel.gestionhotel.modelo.Persona;
import clases.hotel.gestionhotel.modelo.PersonaVO;
import clases.hotel.gestionhotel.modelo.Reserva;
import clases.hotel.gestionhotel.modelo.ReservaVO;

import java.util.ArrayList;

public class Conversor {

    public Persona convertirPersona(PersonaVO personVO){
        Persona person = new Persona();
        person.setDNI(personVO.getDNIVO());
        person.setNombre(personVO.getNombreVO());
        person.setApellidos(personVO.getApellidosVO());
        person.setDireccion(personVO.getDireccionVO());
        person.setLocalidad(personVO.getLocalidadVO());
        person.setProvincia(personVO.getProvinciaVO());
        return person;
    }

    public ArrayList<Persona> listaPersona(ArrayList<PersonaVO> listaPersonVO){
        ArrayList<Persona> listaPerson = new ArrayList<Persona>();
        Persona person = new Persona();
        for(int i=0;i<listaPersonVO.size();i++){
            person=convertirPersona(listaPersonVO.get(i));
            listaPerson.add(i,person);
        }
        return listaPerson;
    }

    public PersonaVO convertirPersonaVO(Persona person){
        PersonaVO personVO = new PersonaVO();
        personVO.setDNIVO(person.getDNI());
        personVO.setNombreVO(person.getNombre());
        personVO.setApellidosVO(person.getApellidos());
        personVO.setDireccionVO(person.getDireccion());
        personVO.setLocalidadVO(person.getLocalidad());
        personVO.setProvinciaVO(person.getProvincia());
        return personVO;
    }





    public Reserva convertirReserva(ReservaVO reservaVO){
        Reserva reserva = new Reserva();
        reserva.setId(reservaVO.getIdVO());
        reserva.setFechaLlegada(reservaVO.getFechaLlegadaVO());
        reserva.setFechaFin(reservaVO.getFechaFinVO());
        reserva.setNumHabitacion(reservaVO.getNumHabitacionVO());
        reserva.setTipHab(reservaVO.getTipHabVO());
        reserva.setFumador(reservaVO.getFumadorVO());
        reserva.setRegAloj(reservaVO.getRegAlojVO());
        reserva.setDNICliente(reservaVO.getDNICliente());
        return reserva;
    }

    public ArrayList<Reserva> listaReserva(ArrayList<ReservaVO> listaReservaVO){
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        Reserva reserva = new Reserva();
        for(int i=0;i<listaReservaVO.size();i++){
            reserva=convertirReserva(listaReservaVO.get(i));
            listaReserva.add(i,reserva);
        }
        return listaReserva;
    }

    public ReservaVO convertirReservaVO(Reserva reserva){
        ReservaVO reservaVO = new ReservaVO();
        reservaVO.setIdVO(reserva.getId());
        reservaVO.setFechaLlegadaVO(reserva.getFechaLlegada());
        reservaVO.setFechaFinVO(reserva.getFechaFin());
        reservaVO.setNumHabitacionVO(reserva.getNumHabitacion());
        reservaVO.setTipHabVO(reserva.getTipHab());
        reservaVO.setFumadorVO(reserva.isFumador());
        reservaVO.setRegAlojVO(reserva.getRegAloj());
        reservaVO.setDNICliente(reserva.getDNICliente());
        return reservaVO;
    }
}
