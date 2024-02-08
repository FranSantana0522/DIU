package com.project.agenda.controller;

import com.project.agenda.model.AgendaVO;

import java.util.List;
import java.util.Optional;

public interface AgendaAPI {
    List<AgendaVO> getAllAgendados();
    Optional<AgendaVO> getAgendadoById(String id);
    AgendaVO save (AgendaVO agendado);
    AgendaVO updateAgendado(AgendaVO agendado,String id);
    void deleteAgendado(String id);
    void deleteAll();
}
