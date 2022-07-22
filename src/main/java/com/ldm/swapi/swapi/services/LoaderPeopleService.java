package com.ldm.swapi.swapi.services;

import com.ldm.swapi.swapi.dtos.PeopleDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface LoaderPeopleService {

    List<PeopleDto> getAll() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException;

    PeopleDto getById(Number peopleId) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
