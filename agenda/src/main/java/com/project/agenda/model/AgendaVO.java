package com.project.agenda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AgendaVO {
    @Id
    private String id;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String direccion;
    private String localidad;
}
