package com.romaniak.marcin.starwars.repository;

import com.romaniak.marcin.starwars.model.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {

  Page<People> findAllByHeightIsLessThan(int height, Pageable pageable);
  
  Page<People> findByNameContaining(String name, Pageable pageable);
}