package com.ldm.swapi.swapi.services;

import com.ldm.swapi.swapi.dtos.FilmDto;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public interface LoaderFilmService {

    List<FilmDto> getAll() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException;

    FilmDto getById(Number filmId) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
