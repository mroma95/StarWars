package com.romaniak.marcin.starwars.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.romaniak.marcin.starwars.dto.PeopleDto
import com.romaniak.marcin.starwars.repository.PeopleRepository
import com.romaniak.marcin.starwars.service.PeopleService
import com.romaniak.marcin.starwars.service.api.PeopleImportDataService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.util.stream.Collectors

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class PeopleControllerTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    PeopleImportDataService service;
    @Autowired
    PeopleService peopleService;
    ObjectMapper mapper = new ObjectMapper();

    def setup(){
        peopleRepository.deleteAll()
        service.getImportedCollection().clear()
    }

    def "Test getting people via GET /api/people/{id}"() {
        given:
        service.importData(id)
        String url = "/api/people/" + id

        when: "GET /api/people/1"
        def result = restTemplate.getForObject(url, PeopleDto.class)

        then:
        result.name == excpectedName

        where:
        id | excpectedName
        1  | "Luke Skywalker"
        2  | "C-3PO"
        3  | "R2-D2"
        4  | "Darth Vader"
        5  | "Leia Organa"
        6  | "Owen Lars"
        7  | "Beru Whitesun lars"
        8  | "R5-D4"
        9  | "Biggs Darklighter"
        10 | "Obi-Wan Kenobi"
    }

    def "Test getting people via GET /api/people/name"() {
        given:
        String url = "/api/people/name?name=" + option
        importData()

        when: "GET /api/people/name"
        ResponseEntity<Object> response =
                restTemplate.getForEntity(url, Object.class);
        LinkedHashMap<String, Object> responseMap = response.getBody();
        ArrayList<PeopleDto> peoples = responseMap.get("content");

        then:
        List<PeopleDto> result = peoples.stream().map(peopleDto -> mapper.convertValue(peopleDto, PeopleDto.class)).collect(Collectors.toList())
        def names = result.stream().map(dto -> dto.name).collect(Collectors.toList())
        names == excpectedPeopleNames

        where:
        option | excpectedPeopleNames
        "Lu"   | ["Luke Skywalker"]
        "lu"   | []
        "3PO"  | ["C-3PO"]
        "Da"   | ["Darth Vader", "Biggs Darklighter"]
        "leia" | []
    }

    def "Test getting people via GET /api/people"() {
        given:
        importData()
        String url = "/api/people"

        when: "GET /api/people"
        ResponseEntity<Object> response =
                restTemplate.getForEntity(url, Object.class);
        LinkedHashMap<String, Object> responseMap = response.getBody();
        ArrayList<PeopleDto> peoples = responseMap.get("content");

        then:
        def heightCollection = peoples.stream()
                .map(peopleDto -> mapper.convertValue(peopleDto, PeopleDto.class))
                .peek(pDto -> println(("name = ${pDto.name}, height = ${pDto.height}, mass = ${pDto.mass}")))
                .map(dto -> dto.height)
                .collect(Collectors.toList())
        heightCollection == [172, 167, 96, 150, 178, 165, 97]
    }

    void importData() {
        service.importData(1L)
        service.importData(2L)
        service.importData(3L)
        service.importData(4L)
        service.importData(5L)
        service.importData(6L)
        service.importData(7L)
        service.importData(8L)
        service.importData(9L)
        service.importData(10L)
    }
}