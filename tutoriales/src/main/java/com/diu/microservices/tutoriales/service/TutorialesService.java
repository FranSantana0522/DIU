package com.diu.microservices.tutoriales.service;


import com.diu.microservices.tutoriales.model.TutorialesVO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TutorialesService {
    List<TutorialesVO> getAllTutorials(String titulo);
    Optional<TutorialesVO> getTutorialsById(String id);
    List<TutorialesVO> findByPublished();
    TutorialesVO save(TutorialesVO tutorial);
    TutorialesVO updateTutorial(TutorialesVO tutorial,String id);
    void deleteTutorial(String id);
    void deleteAllTutorials();
}
