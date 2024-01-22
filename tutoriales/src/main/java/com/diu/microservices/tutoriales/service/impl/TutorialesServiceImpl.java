package com.diu.microservices.tutoriales.service.impl;


import com.diu.microservices.tutoriales.model.TutorialesVO;
import com.diu.microservices.tutoriales.repository.TutorialesRepository;
import com.diu.microservices.tutoriales.service.TutorialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialesServiceImpl implements TutorialesService {

    @Autowired
    private TutorialesRepository tutorialesRepository;

    @Override
    public List<TutorialesVO> getAllTutorials(String titulo) {
        if (titulo != null) {
            return tutorialesRepository.findByTituloContaining(titulo);
        } else {
            return tutorialesRepository.findAll();
        }
    }

    @Override
    public Optional<TutorialesVO> getTutorialsById(String id) {
        return tutorialesRepository.findById(id);
    }

    @Override
    public List<TutorialesVO> findByPublished() {
        return tutorialesRepository.findByPublicadoTrue();
    }

    @Override
    public TutorialesVO save(TutorialesVO tutorial) {
        return tutorialesRepository.save(tutorial);
    }

    @Override
    public TutorialesVO updateTutorial(TutorialesVO tutorial, String id) {
        Optional<TutorialesVO> tutorialEdit = tutorialesRepository.findById(id);
        if (tutorialEdit.isPresent()) {
            TutorialesVO realTutorial = tutorialEdit.get();
            realTutorial.setTitulo(tutorial.getTitulo());
            realTutorial.setDescripcion(tutorial.getDescripcion());
            realTutorial.setPublicado(tutorial.isPublicado());
            return tutorialesRepository.save(realTutorial);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTutorial(String id) {
        tutorialesRepository.deleteById(id);
    }

    @Override
    public void deleteAllTutorials() {
        tutorialesRepository.deleteAll();
    }
}
