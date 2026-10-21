package com.orientacion.universitaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.orientacion.universitaria.IService.IFacultadService;
import com.orientacion.universitaria.entidades.Facultad;

@RestController
@RequestMapping("/api/facultades")
@CrossOrigin(origins = "*")
public class FacultadController {

    @Autowired
    private IFacultadService facultadService;

    @GetMapping
    public List<Facultad> getAllFacultades() {
        return facultadService.findAllFacultades();
    }

    @GetMapping("/{id}")
    public Facultad getFacultadById(@PathVariable int id) {
        return facultadService.findById(id);
    }

    @PostMapping
    public Facultad createFacultad(@RequestBody Facultad facultad) {
        return facultadService.saveFacultad(facultad);
    }
}
