package com.ldm.swapi.swapi.services;

import com.ldm.swapi.infra.apiconsumer.DataType;
import com.ldm.swapi.infra.apiconsumer.ParseSpecField;
import com.ldm.swapi.infra.apiconsumer.SWEntity;
import com.ldm.swapi.swapi.dataprovider.SWApiService;
import com.ldm.swapi.swapi.dtos.PlanetDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoaderPlanetServiceImpl implements LoaderPlanetService {

    private final String BASE_URI = "planets";

    private List<ParseSpecField> getParseSpecField() {

        List<ParseSpecField> specFields = new ArrayList<ParseSpecField>();

        specFields.add(new ParseSpecField("name", "nome", DataType.STRING));
        specFields.add(new ParseSpecField("diameter", "diametro", DataType.STRING));
        specFields.add(new ParseSpecField("rotation_period", "periodoRotacao", DataType.STRING));
        specFields.add(new ParseSpecField("orbital_period", "periodoOrbital", DataType.STRING));
        specFields.add(new ParseSpecField("gravity", "gravidade", DataType.STRING));
        specFields.add(new ParseSpecField("population", "populacao", DataType.Date));
        specFields.add(new ParseSpecField("climate", "clima", DataType.List));
        specFields.add(new ParseSpecField("terrain", "terreno", DataType.List));
        specFields.add(new ParseSpecField("surface_water", "aguaSuperficie", DataType.List));
        specFields.add(new ParseSpecField("residents", "moradores", DataType.List));
        specFields.add(new ParseSpecField("films", "filmes", DataType.DateTime));
        specFields.add(new ParseSpecField("url", "url", DataType.STRING));
        specFields.add(new ParseSpecField("created", "criado", DataType.DateTime));
        specFields.add(new ParseSpecField("edited", "editado", DataType.DateTime));

        return specFields;
    }

    @Override
    public List<PlanetDto> getAll()
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        String uri = String.format("/%s", BASE_URI);
        String resultFieldName = "results";

        SWApiService swApiService = new SWApiService();
        List<SWEntity> resultList = swApiService.getAllSync(
                uri, resultFieldName, PlanetDto.class, this.getParseSpecField());

        List<PlanetDto> planets = new ArrayList<PlanetDto>();

        resultList.stream().forEach(item ->
               planets.add((PlanetDto)item)
        );

        return planets;
    }

    @Override
    public PlanetDto getById(Number planetId) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String uri = String.format("/%s/%s", BASE_URI, planetId);

        SWApiService swApiService = new SWApiService();
        PlanetDto planetDto = (PlanetDto) swApiService.getOneSync(
                uri, PlanetDto.class, this.getParseSpecField());

        return planetDto;
    }
}

//tBDtxfnKGmU7K6ES