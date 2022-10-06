package com.romaniak.marcin.starwars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDto extends ApiDto{

  private Integer height;

  private Integer mass;
}