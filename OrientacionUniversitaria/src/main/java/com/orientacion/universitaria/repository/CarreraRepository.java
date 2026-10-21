package com.orientacion.universitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orientacion.universitaria.entidades.Carrera;
import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    List<Carrera> findByAreaEstudioContainingIgnoreCase(String area);
}
