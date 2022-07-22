package com.ldm.swapi.apiconsumer;

import reactor.core.publisher.Mono;

public interface LDMApiConsumer {

    Mono<Object> getDataAsync(String uri);

    Object getDataSync(String uri);
}
