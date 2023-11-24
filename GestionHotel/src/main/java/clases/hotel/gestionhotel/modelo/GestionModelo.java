package clases.hotel.gestionhotel.modelo;

import clases.hotel.gestionhotel.modelo.repository.GestionRepository;

import java.util.ArrayList;

public class GestionModelo {
    GestionRepository gestionRepository;

    /**
     * Constructor de gestion modelo vacio
     */
    public GestionModelo() {
    }


    // GESTION DE PERSONAS

    /**
     * Metodo para listar PersonasVO de la gestionRepository
     * @return
     * @throws ExceptionGH
     */
    public ArrayList<PersonaVO> listarPersonas() throws ExceptionGH {
        return gestionRepository.ObtenerListaPersonaVO();
    }

    /**
     * Metodo para crear la personaVO de la gestionRepository
     * @param personaVO
     * @throws ExceptionGH
     */
    public void crearPersonaVO(PersonaVO personaVO) throws ExceptionGH {
        gestionRepository.addPersonaVO(personaVO);
    }

    /**
     * Metodo para editar la personaVO de la gestionRepository
     * @param personaVO
     * @throws ExceptionGH
     */
    public void editarPersonaVO(PersonaVO personaVO) throws ExceptionGH {
        gestionRepository.editPersonaVO(personaVO);
    }
    public void deletePersonaVO(PersonaVO personaVO) throws ExceptionGH {
        gestionRepository.deletePersonaVO(personaVO.getDNIVO());
    }

    /**
     * Metodo para buscar una persona por el DNI
     * @param personaVO
     * @return
     * @throws ExceptionGH
     */
    public PersonaVO busquedaPersonaDNI(PersonaVO personaVO) throws ExceptionGH{
        personaVO=gestionRepository.busquedaPersonaVO(personaVO.getDNIVO());
        return personaVO;
    }

    // GESTION DE RESERVAS

    /**
     * Metodo para listar reservasVO de la gestionRepository
     * @param dniC
     * @return
     * @throws ExceptionGH
     */
    public ArrayList<ReservaVO> listarReservas(String dniC) throws ExceptionGH {
        return gestionRepository.ObtenerListaReservaVO(dniC);
    }

    /**
     * Metodo para crear la reservaVO de la gestionRepository
     * @param reservaVO
     * @throws ExceptionGH
     */
    public void crearReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        gestionRepository.addReservaVO(reservaVO);
    }

    /**
     * Metodo para editar la reservaVO de la gestionRepository
     * @param reservaVO
     * @throws ExceptionGH
     */
    public void editarReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        gestionRepository.editReservaVO(reservaVO);
    }

    /**
     * Metodo para borrar la reservaVO de la gestionRepository
     * @param reservaVO
     * @throws ExceptionGH
     */
    public void deleteReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        gestionRepository.deleteReservaVO(reservaVO.getIdVO());
    }

    /**
     * Metodo para recoger el id de la reserva
     * @return
     * @throws ExceptionGH
     */
    public int lastIdReserva() throws ExceptionGH {
        return gestionRepository.lastIdReservaVO();
    }

    public void setImpl(GestionRepository inter) {
        this.gestionRepository = inter;
    }
}