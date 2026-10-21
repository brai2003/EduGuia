package com.orientacion.universitaria.IService;

import java.util.List;
import com.orientacion.universitaria.entidades.Facultad;

public interface IFacultadService {
    List<Facultad> findAllFacultades();
    Facultad saveFacultad(Facultad facultad);
    Facultad findById(int id);
}
