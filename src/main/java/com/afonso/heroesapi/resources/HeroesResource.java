package com.afonso.heroesapi.resources;

import com.afonso.heroesapi.constants.HeroesConstants;
import com.afonso.heroesapi.document.Heroes;
import com.afonso.heroesapi.service.HeroesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/heroes")
public class HeroesResource {
    private final HeroesService service;
    private static Logger logger = LoggerFactory.getLogger(HeroesResource.class);

    @GetMapping()
    public Flux<Heroes> getAll(){
        logger.info("Requesting a list of all heroes");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> getById(@PathVariable String id){
        logger.info("Requesting a hero by id");
        return service.findById(id)
                .map((item)-> new ResponseEntity<>(item,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public Mono<ResponseEntity<Heroes>> save(@RequestBody Heroes hero){
        logger.info("Requesting save a new hero");
        return service.save(hero)
                .map((item)-> new ResponseEntity<>(item,HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Boolean>> remove(@PathVariable String id){
        logger.info("Requesting remove a new hero");
        return service.deleteById(id)
                .map((item)-> new ResponseEntity<>(item,HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
    }
}
