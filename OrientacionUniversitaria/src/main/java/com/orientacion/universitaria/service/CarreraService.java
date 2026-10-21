package com.orientacion.universitaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orientacion.universitaria.IService.ICarreraService;
import com.orientacion.universitaria.entidades.Carrera;
import com.orientacion.universitaria.repository.CarreraRepository;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> findAllCarreras() {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera saveCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    public List<Carrera> findByArea(String area) {
        return carreraRepository.findByAreaEstudioContainingIgnoreCase(area);
    }
}
