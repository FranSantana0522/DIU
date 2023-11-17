package clases.hotel.gestionhotel.modelo.repository;

import clases.hotel.gestionhotel.modelo.ExceptionGH;
import clases.hotel.gestionhotel.modelo.PersonaVO;
import clases.hotel.gestionhotel.modelo.ReservaVO;

import java.util.ArrayList;

public interface GestionRepository {
    ArrayList<PersonaVO> ObtenerListaPersonaVO() throws ExceptionGH;
    ArrayList<ReservaVO> ObtenerListaReservaVO(String dniC) throws ExceptionGH;

    void addPersonaVO(PersonaVO var1) throws ExceptionGH;

    void addReservaVO(ReservaVO var1) throws ExceptionGH;

    void deletePersonaVO(String var1) throws ExceptionGH;

    void deleteReservaVO(Integer var1) throws ExceptionGH;

    void editPersonaVO(PersonaVO var1) throws ExceptionGH;

    void editReservaVO(ReservaVO var1) throws ExceptionGH;

    int lastIdReservaVO() throws ExceptionGH;

    int DNIpersonaVO() throws ExceptionGH;
}
