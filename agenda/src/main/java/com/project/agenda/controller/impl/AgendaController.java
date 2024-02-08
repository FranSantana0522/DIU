package com.project.agenda.controller.impl;

import com.project.agenda.controller.AgendaAPI;
import com.project.agenda.model.AgendaVO;
import com.project.agenda.repository.AgendaRepository;
import com.project.agenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AgendaController implements AgendaAPI {
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private AgendaRepository agendaRepository;
    @Override
    @GetMapping("/agenda")
    public List<AgendaVO> getAllAgendados() {
        return agendaService.getAllAgendados();
    }

    @Override
    @GetMapping("/agenda/{id}")
    public Optional<AgendaVO> getAgendadoById(@PathVariable String id) {
        return agendaService.getAgendadoById(id);
    }

    @Override
    @PostMapping("/agenda")
    public AgendaVO save(@RequestBody AgendaVO agendado) {
        return agendaService.save(agendado);
    }

    @Override
    @PutMapping("/agenda/{id}")
    public AgendaVO updateAgendado(@RequestBody AgendaVO agendado,@PathVariable String id) {
        return agendaService.updateAgendado(agendado,id);
    }

    @Override
    @DeleteMapping("/agenda/{id}")
    public void deleteAgendado(@PathVariable String id) {
        agendaService.deleteAgendado(id);
    }

    @Override
    @DeleteMapping("/agenda")
    public void deleteAll() {
        agendaService.deleteAll();
    }
}
