package com.ldm.swapi.swapi.controller;

import com.ldm.swapi.swapi.services.LoaderPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/swapi/planets")
public class PlanetController {

    @Autowired
    private LoaderPlanetService loaderPlanetService;

    @GetMapping
    public ResponseEntity<Object> getAll() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        return ResponseEntity.status(HttpStatus.OK).body(this.loaderPlanetService.getAll());
    }

    @GetMapping("/{planetId}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "planetId") Long planetId)
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        return ResponseEntity.status(HttpStatus.OK).body(this.loaderPlanetService.getById(planetId));
    }
}
