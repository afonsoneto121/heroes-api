package com.afonso.heroesapi.service;

import com.afonso.heroesapi.document.Heroes;
import com.afonso.heroesapi.repository.HeroesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {
    private HeroesRepository repository;

    public HeroesService(HeroesRepository repository) {
        this.repository = repository;
    }

    public Flux<Heroes> findAll() {
        return Flux.fromIterable(repository.findAll());
    }

    public Mono<Heroes> findById(String id) {
        return Mono.justOrEmpty(repository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes) {
        return Mono.justOrEmpty(repository.save(heroes));
    }

    public Mono<Boolean> deleteById(String id) {
        repository.deleteById(id);
        return Mono.just(true);
    }
}
