package com.romaniak.marcin.starwars.mapper;

import com.romaniak.marcin.starwars.dto.PeopleDto;
import com.romaniak.marcin.starwars.model.People;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeopleMapper {

  PeopleMapper INSTANCE = Mappers.getMapper(PeopleMapper.class);

  PeopleDto toDto(People people);

  People toEntity(PeopleDto dto);
}