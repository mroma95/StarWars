package com.romaniak.marcin.starwars.controller;

import com.romaniak.marcin.starwars.service.api.PeopleImportDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people/import")
public class PeopleImportController {

  private final PeopleImportDataService peopleImportDataService;

  @GetMapping("/{id}")
  public void importPeople(@PathVariable Long id) {
    peopleImportDataService.importData(id);
  }
}