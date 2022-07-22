package com.ldm.swapi.swapi.dtos;

import com.ldm.swapi.apiconsumer.SWEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmDto extends SWEntity {

    private String titulo;
    private Number episodioId;
    private String aberturaRastreamento;
    private String diretor;
    private String produtor;
    private String dataVersao;
    private List<String> caracteres;
    private List<String> planetas;
    private List<String> especies;
    private List<String> veiculos;
    private List<String> navesEstelares;
    private String criado;
    private String editado;
    private String url;
}
