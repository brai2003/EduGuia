package com.orientacion.universitaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.orientacion.universitaria.IService.ICarreraService;
import com.orientacion.universitaria.entidades.Carrera;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin(origins = "*")
public class CarreraController {

    @Autowired
    private ICarreraService carreraService;

    @GetMapping
    public List<Carrera> getAllCarreras(@RequestParam(required = false) String area) {
        if (area != null && !area.isEmpty()) {
            return carreraService.findByArea(area);
        }
        return carreraService.findAllCarreras();
    }

    @PostMapping
    public Carrera createCarrera(@RequestBody Carrera carrera) {
        return carreraService.saveCarrera(carrera);
    }
}
