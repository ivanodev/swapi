package com.ldm.swapi.swapi.dtos;

import com.ldm.swapi.apiconsumer.SWEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeopleDto extends SWEntity {

    private String nome;
    private String dataNascimento;
    private String corOlho;
    private String genero;
    private String corCabelo;
    private String altura;
    private String peso;
    private String corPele;
    private String planetaNatal;
    private List<String> filmes;
    private List<String> especies;
    private List<String> navesEstelares;
    private List<String> veiculos;
}
