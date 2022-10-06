package com.romaniak.marcin.starwars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "peoples", schema = "star_wars")
@Data
public class People {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_id_generator")
  @SequenceGenerator(name = "people_id_generator", sequenceName = "star_wars.people_id_sequence", allocationSize = 1)
  @Column(name = "id", updatable = false, nullable = false, insertable = false)
  private Long id;

  private String name;

  private Integer height;

  private Integer mass;
}