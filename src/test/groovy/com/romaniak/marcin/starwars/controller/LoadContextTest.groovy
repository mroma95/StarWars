package com.romaniak.marcin.starwars.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired
    private PeopleController peopleController;
    @Autowired
    private PeopleImportController peopleImportController;

    def "when context is loaded then all expected beans are created"() {
        given:
        expect: "the applicationController is created"
        peopleController
        peopleImportController
    }
}