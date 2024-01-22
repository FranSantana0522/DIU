package com.diu.microservices.tutoriales.repository;


import com.diu.microservices.tutoriales.model.TutorialesVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialesRepository extends MongoRepository<TutorialesVO,String> {
    List<TutorialesVO> findByPublicadoTrue();

    List<TutorialesVO> findByTituloContaining(String titulo);
}
