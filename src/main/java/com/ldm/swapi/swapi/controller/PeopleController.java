package com.ldm.swapi.swapi.controller;

import com.ldm.swapi.swapi.services.LoaderPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/swapi/peoples")
public class PeopleController {

    @Autowired
    private LoaderPeopleService loaderPeopleService;

    @GetMapping
    public ResponseEntity<Object> getAll() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        return ResponseEntity.status(HttpStatus.OK).body(this.loaderPeopleService.getAll());
    }

    @GetMapping("/{peopleId}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "peopleId") Long peopleId)
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        return ResponseEntity.status(HttpStatus.OK).body(this.loaderPeopleService.getById(peopleId));
    }
}
