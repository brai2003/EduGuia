package com.orientacion.universitaria.IService;

import java.util.List;
import com.orientacion.universitaria.entidades.Carrera;

public interface ICarreraService {
    List<Carrera> findAllCarreras();
    Carrera saveCarrera(Carrera carrera);
    List<Carrera> findByArea(String area);
}
