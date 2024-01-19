package com.diu.microservices.tutoriales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TutorialesVO {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private boolean publicado;
}
