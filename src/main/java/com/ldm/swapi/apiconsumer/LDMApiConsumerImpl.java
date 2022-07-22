package com.ldm.swapi.apiconsumer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class LDMApiConsumerImpl implements LDMApiConsumer {

    private WebClient webClient;

    public LDMApiConsumerImpl(String baseURL) {

        this.webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<Object> getDataAsync(String uri) {

        return this.webClient
                .method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Object getDataSync(String uri) {

        return this.webClient
                .method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
