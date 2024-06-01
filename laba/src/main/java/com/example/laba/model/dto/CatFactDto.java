package com.example.laba.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Cat fact dto. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatFactDto {
  private Long id;
  private String fact;
}
