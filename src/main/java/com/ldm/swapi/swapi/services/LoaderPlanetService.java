package com.ldm.swapi.swapi.services;

import com.ldm.swapi.swapi.dtos.PlanetDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface LoaderPlanetService {

    List<PlanetDto> getAll() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException;
    PlanetDto getById(Number planetId) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
