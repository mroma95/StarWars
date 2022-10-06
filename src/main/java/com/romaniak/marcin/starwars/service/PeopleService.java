package com.romaniak.marcin.starwars.service;

import com.romaniak.marcin.starwars.dto.PeopleDto;
import com.romaniak.marcin.starwars.mapper.PeopleMapper;
import com.romaniak.marcin.starwars.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j //TODO cos zalogowac albo usunac
@RequiredArgsConstructor
public class PeopleService {

  private final PeopleRepository peopleRepository;
  private final PeopleMapper peopleMapper;
//  @Value("${base.height}")
//  private final int baseHeight;


  @Transactional(readOnly = true)
  public Page<PeopleDto> findAllByHeight(int baseHeight,int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
//    return peopleRepository.findAllByHeightIsLessThan(baseHeight, pageable)
//        .map(PeopleMapper.INSTANCE::toDto);

    return peopleRepository.findAllByHeightIsLessThan(baseHeight, pageable)
        .map(PeopleMapper.INSTANCE::toDto);
  }

  @Transactional(readOnly = true)
  public PeopleDto findById(Long id) {
    return PeopleMapper.INSTANCE.toDto(peopleRepository.getOne(id));
  }

  @Transactional(readOnly = true)
  public Page<PeopleDto> findByName(String name, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return peopleRepository.findByNameContaining(name, pageable)
        .map(PeopleMapper.INSTANCE::toDto);
  }
}