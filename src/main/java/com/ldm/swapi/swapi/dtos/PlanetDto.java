package com.ldm.swapi.swapi.dtos;

import com.ldm.swapi.apiconsumer.SWEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanetDto extends SWEntity {

    private String nome;
    private String diametro;
    private String periodoRotacao;
    private String periodoOrbital;
    private String gravidade;
    private String populacao;
    private String clima;
    private String terreno;
    private String aguaSuperficie;
    private List<String> moradores;
    private List<String> filmes;
}
