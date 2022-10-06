package com.romaniak.marcin.starwars.service.api


import com.romaniak.marcin.starwars.exception.IllegalUserActionException
import com.romaniak.marcin.starwars.repository.PeopleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import spock.lang.Specification

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PeopleImportDataServiceTest extends Specification {

    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    PeopleImportDataService service;

    def setup(){
        peopleRepository.deleteAll()
        service.getImportedCollection().clear()
    }

    def "Import peoples via GET /api/people/import/{id}"() {
        given:
        importData()

        when:
        def all = peopleRepository.findAll()

        then: "The result is 10"
        all.size() == 10
    }

    def "Adding duplicate people should throw IllegalUserActionException"() {
        given:
        service.importData(1)
        when:
        service.importData(1)
        then:
        thrown(IllegalUserActionException.class)
    }

    def "Adding people which does not exist in external API should throw IllegalUserActionException"() {
        when:
        service.importData(100)
        then:
        thrown(IllegalUserActionException.class)
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