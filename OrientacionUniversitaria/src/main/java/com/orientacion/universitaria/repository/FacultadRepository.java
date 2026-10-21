package com.orientacion.universitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orientacion.universitaria.entidades.Facultad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {
}
