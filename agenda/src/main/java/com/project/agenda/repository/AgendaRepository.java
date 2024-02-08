package com.project.agenda.repository;

import com.project.agenda.model.AgendaVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AgendaRepository extends MongoRepository<AgendaVO,String> {

    List<AgendaVO> findByNombre();

    List<AgendaVO> findByNombreContaining(String nombre);
}
