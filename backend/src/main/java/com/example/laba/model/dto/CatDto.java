package com.example.laba.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Cat dto. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
  private Long id;
  private String name;
  private Integer age;

  /**
   * Instantiates a new Cat dto.
   *
   * @param name the name
   */
  public CatDto(String name) {
    this.name = name;
  }

  /**
   * Instantiates a new Cat dto.
   *
   * @param id the id
   * @param name the name
   * @param age the age
   */
  public CatDto(Long id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @JsonManagedReference private List<CatFactDto> facts;
  @JsonManagedReference private List<OwnerDto> owners;
}
