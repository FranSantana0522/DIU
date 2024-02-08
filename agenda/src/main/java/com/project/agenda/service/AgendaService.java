package com.project.agenda.service;

import com.project.agenda.model.AgendaVO;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    List<AgendaVO> getAllAgendados();
    Optional<AgendaVO> getAgendadoById(String id);
    AgendaVO save (AgendaVO agendado);
    AgendaVO updateAgendado(AgendaVO agendado,String id);
    void deleteAgendado(String id);
    void deleteAll();
}
