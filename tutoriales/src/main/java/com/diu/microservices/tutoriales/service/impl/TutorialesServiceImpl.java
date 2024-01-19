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
    public List<TutorialesVO> getAllTutorials() {
        return tutorialesRepository.findAll();
    }

    @Override
    public Optional<TutorialesVO> getTutorialsById(String id) {
        return Optional.empty();
    }

    @Override
    public List<TutorialesVO> findByPublished() {
        return null;
    }

    @Override
    public TutorialesVO save(TutorialesVO tutorial) {
        return tutorialesRepository.save(tutorial);
    }

    @Override
    public TutorialesVO updateTutorial(TutorialesVO tutorial, String id) {
        return null;
    }

    @Override
    public ResponseEntity deleteTutorial(String id) {
        return null;
    }

    @Override
    public ResponseEntity deleteAllTutorials() {
        return null;
    }
}
