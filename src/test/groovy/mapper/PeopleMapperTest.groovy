package mapper

import com.romaniak.marcin.starwars.dto.PeopleDto
import com.romaniak.marcin.starwars.mapper.PeopleMapper
import com.romaniak.marcin.starwars.model.People
import spock.lang.Specification

class PeopleMapperTest extends Specification {

    def "map to dto"() {
        given:
        People people = createPeople()
        when:
        def dto = PeopleMapper.INSTANCE.toDto(people)
        then:
        dto.getName() == "test"
        dto.getHeight() == 190
        dto.getMass() == 100
    }

    def "map to Entity"() {
        given:
        PeopleDto dto = createPeopleDto()
        when:
        def people = PeopleMapper.INSTANCE.toEntity(dto)
        then:
        people.getName() == "test"
        people.getHeight() == 190
        people.getMass() == 100
    }

    People createPeople() {
        People people = new People()
        people.setId(1L)
        people.setName("test")
        people.setHeight(190)
        people.setMass(100)
        return people
    }

    PeopleDto createPeopleDto() {
        PeopleDto peopleDto = new PeopleDto()
        peopleDto.setName("test")
        peopleDto.setHeight(190)
        peopleDto.setMass(100)
        return peopleDto
    }


}

