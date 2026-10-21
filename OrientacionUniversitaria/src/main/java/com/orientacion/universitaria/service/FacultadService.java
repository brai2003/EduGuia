package com.orientacion.universitaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orientacion.universitaria.IService.IFacultadService;
import com.orientacion.universitaria.entidades.Facultad;
import com.orientacion.universitaria.repository.FacultadRepository;

@Service
public class FacultadService implements IFacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    @Override
    public List<Facultad> findAllFacultades() {
        return facultadRepository.findAll();
    }

    @Override
    public Facultad saveFacultad(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public Facultad findById(int id) {
        return facultadRepository.findById(id).orElse(null);
    }
}
