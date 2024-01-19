package com.diu.microservices.tutoriales.controller.impl;


import com.diu.microservices.tutoriales.controller.TutorialesAPI;
import com.diu.microservices.tutoriales.model.TutorialesVO;
import com.diu.microservices.tutoriales.repository.TutorialesRepository;
import com.diu.microservices.tutoriales.service.TutorialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TutorialesController implements TutorialesAPI {

    @Autowired
    private TutorialesService tutorialesService;

    @Autowired
    private TutorialesRepository tutorialesRepository;

    @Override
    @GetMapping("/tutorials")
    public List<TutorialesVO> getAllTutorials(){
        return tutorialesService.getAllTutorials();
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
    @PostMapping("/tutorials")
    public TutorialesVO save(@RequestBody TutorialesVO tutorial) {
        return tutorialesService.save(tutorial);
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
