package com.ldm.swapi.swapi.controller;

import com.ldm.swapi.swapi.services.LoaderFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/swapi/films")
public class FilmController {

    @Autowired
    private LoaderFilmService loaderFilmService;

    @GetMapping
    public ResponseEntity<Object> getAll() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        return ResponseEntity.status(HttpStatus.OK).body(this.loaderFilmService.getAll());
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "filmId") Long filmId)
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        return ResponseEntity.status(HttpStatus.OK).body(this.loaderFilmService.getById(filmId));
    }
}
