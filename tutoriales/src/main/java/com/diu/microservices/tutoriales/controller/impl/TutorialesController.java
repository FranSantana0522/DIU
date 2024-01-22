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
    public List<TutorialesVO> getAllTutorials(@RequestParam(required = false)String titulo){
        return tutorialesService.getAllTutorials(titulo);
    }

    @Override
    @GetMapping("/tutorial/{id}")
    public Optional<TutorialesVO> getTutorialsById(@PathVariable  String id) {
        return tutorialesService.getTutorialsById(id);
    }

    @Override
    public List<TutorialesVO> findByPublished() {
        return tutorialesService.findByPublished();
    }

    @Override
    @PostMapping("/tutorials")
    public TutorialesVO save(@RequestBody TutorialesVO tutorial) {
        return tutorialesService.save(tutorial);
    }

    @Override
    @PutMapping("/editTuto/{id}")
    public TutorialesVO updateTutorial(@RequestBody TutorialesVO tutorial,@PathVariable  String id) {
        return tutorialesService.updateTutorial(tutorial,id);
    }

    @Override
    @DeleteMapping("/deleteTuto/{id}")
    public void deleteTutorial(@PathVariable  String id) {
        tutorialesService.deleteTutorial(id);
    }

    @Override
    @DeleteMapping("/deleteAll")
    public void deleteAllTutorials() {
        tutorialesService.deleteAllTutorials();
    }
}
