package com.example.laba.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Owner dto. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
  private Long id;
  private String name;

  public OwnerDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @JsonBackReference private List<CatDto> cats;
}
