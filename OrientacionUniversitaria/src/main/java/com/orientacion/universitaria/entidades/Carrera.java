package com.orientacion.universitaria.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int duracionAnos;
    private String areaEstudio;
    private String modalidad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;

    public Carrera() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getDuracionAnos() { return duracionAnos; }
    public void setDuracionAnos(int duracionAnos) { this.duracionAnos = duracionAnos; }

    public String getAreaEstudio() { return areaEstudio; }
    public void setAreaEstudio(String areaEstudio) { this.areaEstudio = areaEstudio; }

    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }
}
