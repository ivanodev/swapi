package com.ldm.swapi.swapi.services;

import com.ldm.swapi.infra.apiconsumer.DataType;
import com.ldm.swapi.infra.apiconsumer.ParseSpecField;
import com.ldm.swapi.infra.apiconsumer.SWEntity;
import com.ldm.swapi.swapi.dataprovider.SWApiService;
import com.ldm.swapi.swapi.dtos.FilmDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoaderFilmServiceImpl implements LoaderFilmService {

    private final String BASE_URI = "films";

    private List<ParseSpecField> getParseSpecField() {

        List<ParseSpecField> specFields = new ArrayList<ParseSpecField>();

        specFields.add(new ParseSpecField("title", "titulo", DataType.STRING));
        specFields.add(new ParseSpecField("episode_id", "episodioId", DataType.STRING));
        specFields.add(new ParseSpecField("opening_crawl", "aberturaRastreamento", DataType.STRING));
        specFields.add(new ParseSpecField("director", "diretor", DataType.STRING));
        specFields.add(new ParseSpecField("producer", "produtor", DataType.STRING));
        specFields.add(new ParseSpecField("release_date", "dataVersao", DataType.Date));
        specFields.add(new ParseSpecField("characters", "caracteres", DataType.List));
        specFields.add(new ParseSpecField("planets", "planetas", DataType.List));
        specFields.add(new ParseSpecField("starships", "navesEstelares", DataType.List));
        specFields.add(new ParseSpecField("vehicles", "veiculos", DataType.List));
        specFields.add(new ParseSpecField("created", "criado", DataType.DateTime));
        specFields.add(new ParseSpecField("species", "especies", DataType.List));
        specFields.add(new ParseSpecField("edited", "editado", DataType.DateTime));
        specFields.add(new ParseSpecField("url", "url", DataType.STRING));

        return specFields;
    }

    @Override
    public List<FilmDto> getAll()
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        String uri = String.format("/%s", BASE_URI);
        String resultFieldName = "results";

        SWApiService swApiService = new SWApiService();
        List<SWEntity> resultList = swApiService.getAllSync(
                uri, resultFieldName, FilmDto.class, this.getParseSpecField());

        List<FilmDto> films = new ArrayList<FilmDto>();

        resultList.stream().forEach(item ->
               films.add((FilmDto)item)
        );

        return films;
    }

    @Override
    public FilmDto getById(Number filmId) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String uri = String.format("/%s/%s", BASE_URI, filmId);

        SWApiService swApiService = new SWApiService();
        FilmDto filmDto = (FilmDto) swApiService.getOneSync(
                uri, FilmDto.class, this.getParseSpecField());

        return filmDto;
    }
}
