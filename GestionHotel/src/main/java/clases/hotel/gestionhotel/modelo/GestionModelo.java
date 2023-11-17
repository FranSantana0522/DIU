package clases.hotel.gestionhotel.modelo;

import clases.hotel.gestionhotel.modelo.repository.GestionRepository;

import java.util.ArrayList;

public class GestionModelo {
    GestionRepository gestionRepository;

    public GestionModelo() {
    }


    // GESTION DE PERSONAS

    public ArrayList<PersonaVO> listarPersonas() throws ExceptionGH {
        return gestionRepository.ObtenerListaPersonaVO();
    }
    public void crearPersonaVO(PersonaVO personaVO) throws ExceptionGH {
        gestionRepository.addPersonaVO(personaVO);
    }
    public void editarPersonaVO(PersonaVO personaVO) throws ExceptionGH {
        gestionRepository.editPersonaVO(personaVO);
    }
    public void deletePersonaVO(PersonaVO personaVO) throws ExceptionGH {
        gestionRepository.deletePersonaVO(personaVO.getDNIVO());
    }


    // GESTION DE RESERVAS

    public ArrayList<ReservaVO> listarReservas(String dniC) throws ExceptionGH {
        return gestionRepository.ObtenerListaReservaVO(dniC);
    }
    public void crearReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        gestionRepository.addReservaVO(reservaVO);
    }
    public void editarReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        gestionRepository.editReservaVO(reservaVO);
    }
    public void deleteReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        gestionRepository.deleteReservaVO(reservaVO.getIdVO());
    }
    public int lastIdReserva() throws ExceptionGH {
        return gestionRepository.lastIdReservaVO();
    }

    public void setImpl(GestionRepository inter) {
        this.gestionRepository = inter;
    }
}
