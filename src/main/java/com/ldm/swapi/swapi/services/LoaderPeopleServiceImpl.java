package com.ldm.swapi.swapi.services;

import com.ldm.swapi.apiconsumer.DataType;
import com.ldm.swapi.apiconsumer.ParseSpecField;
import com.ldm.swapi.apiconsumer.SWEntity;
import com.ldm.swapi.swapi.dataprovider.SWApiService;
import com.ldm.swapi.swapi.dtos.PeopleDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoaderPeopleServiceImpl implements LoaderPeopleService {

    private final String BASE_URI = "people";

    private List<ParseSpecField> getParseSpecField() {

        List<ParseSpecField> specFields = new ArrayList<ParseSpecField>();

        specFields.add(new ParseSpecField("name", "nome", DataType.STRING));
        specFields.add(new ParseSpecField("birth_year", "dataNascimento", DataType.STRING));
        specFields.add(new ParseSpecField("eye_color", "corOlho", DataType.STRING));
        specFields.add(new ParseSpecField("gender", "genero", DataType.STRING));
        specFields.add(new ParseSpecField("hair_color", "corCabelo", DataType.STRING));
        specFields.add(new ParseSpecField("height", "altura", DataType.Date));
        specFields.add(new ParseSpecField("mass", "peso", DataType.List));
        specFields.add(new ParseSpecField("skin_color", "corPele", DataType.List));
        specFields.add(new ParseSpecField("homeworld", "planetaNatal", DataType.List));
        specFields.add(new ParseSpecField("films", "filmes", DataType.List));
        specFields.add(new ParseSpecField("species", "especies", DataType.DateTime));
        specFields.add(new ParseSpecField("starships", "navesEstelares", DataType.List));
        specFields.add(new ParseSpecField("vehicles", "veiculos", DataType.DateTime));
        specFields.add(new ParseSpecField("url", "url", DataType.STRING));
        specFields.add(new ParseSpecField("created", "criado", DataType.DateTime));
        specFields.add(new ParseSpecField("edited", "editado", DataType.DateTime));

        return specFields;
    }

    @Override
    public List<PeopleDto> getAll()
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        String uri = String.format("/%s", BASE_URI);
        String resultFieldName = "results";

        SWApiService swApiService = new SWApiService();
        List<SWEntity> resultList = swApiService.getAllSync(
                uri, resultFieldName, PeopleDto.class, this.getParseSpecField());

        List<PeopleDto> peoples = new ArrayList<PeopleDto>();

        resultList.stream().forEach(item ->
                peoples.add((PeopleDto) item)
        );

        return peoples;
    }

    @Override
    public PeopleDto getById(Number peopleId) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String uri = String.format("/%s/%s", BASE_URI, peopleId);

        SWApiService swApiService = new SWApiService();
        PeopleDto peopleDto = (PeopleDto) swApiService.getOneSync(
                uri, PeopleDto.class, this.getParseSpecField());

        return peopleDto;
    }
}
