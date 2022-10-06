package com.romaniak.marcin.starwars.controller;

import com.romaniak.marcin.starwars.dto.PeopleDto;
import com.romaniak.marcin.starwars.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PeopleController {

  private final PeopleService peopleService;

  @GetMapping(produces = "application/json")
  public Page<PeopleDto> findAllByHeight(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size,
      @Value("${base.height}") int baseHeight) {
    return peopleService.findAllByHeight(baseHeight, page, size);
  }

  @GetMapping("/{id}")
  public PeopleDto findById(@PathVariable Long id) {
    return peopleService.findById(id);
  }


  @GetMapping(value ="/name" ,produces = "application/json")
  public Page<PeopleDto> findByName(@RequestParam String name,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return peopleService.findByName(name, page, size);
  }

}