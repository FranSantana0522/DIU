package com.project.agenda.service.impl;

import com.project.agenda.model.AgendaVO;
import com.project.agenda.repository.AgendaRepository;
import com.project.agenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;
    @Override
    public List<AgendaVO> getAllAgendados() {
        return agendaRepository.findAll();
    }

    @Override
    public Optional<AgendaVO> getAgendadoById(String id) {
        return agendaRepository.findById(id);
    }

    @Override
    public AgendaVO save(AgendaVO agendado) {
        return agendaRepository.save(agendado);
    }

    @Override
    public AgendaVO updateAgendado(AgendaVO agendado, String id) {
        Optional<AgendaVO> agendadoEdit = agendaRepository.findById(id);
        if (agendadoEdit.isPresent()){
            AgendaVO agendadoReal= agendadoEdit.get();
            agendadoReal.setNombre(agendado.getNombre());
            agendadoReal.setApellidos(agendado.getApellidos());
            agendadoReal.setDireccion(agendado.getDireccion());
            agendadoReal.setLocalidad(agendado.getLocalidad());
            agendadoReal.setFechaNacimiento(agendado.getFechaNacimiento());
            return agendaRepository.save(agendadoReal);
        }else {
            return null;
        }
    }

    @Override
    public void deleteAgendado(String id) {
        agendaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        agendaRepository.deleteAll();
    }
}
