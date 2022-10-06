package com.romaniak.marcin.starwars.controller

import com.romaniak.marcin.starwars.dto.PeopleDto
import com.romaniak.marcin.starwars.repository.PeopleRepository
import com.romaniak.marcin.starwars.service.api.PeopleImportDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PeopleImportControllerTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    PeopleImportDataService service;

    def setup(){
        peopleRepository.deleteAll()
        service.getImportedCollection().clear()
    }

    def "Test importing people via GET /api/people/import/{id}"() {
        given:
        String url = "/api/people/import/1"

        when: "GET /api/people/import/1"
        restTemplate.getForObject(url, PeopleDto.class)

        then:
        peopleRepository.findAll().size() == 1
    }
}