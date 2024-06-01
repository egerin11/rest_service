package com.example.laba.test_dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.laba.model.dto.CatDto;
import com.example.laba.model.dto.CatFactDto;
import com.example.laba.model.dto.OwnerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestDto {

  @Test
  void testCatFactDtoSerialization() throws JsonProcessingException {
    CatFactDto catFactDto = new CatFactDto(1L, "Cats have excellent night vision.");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(catFactDto);

    assertTrue(json.contains("\"id\":1"));
    assertTrue(json.contains("\"fact\":\"Cats have excellent night vision.\""));
  }

  @Test
  void testCatFactDtoDeserialization() throws JsonProcessingException {

    String json = "{\"id\":1,\"fact\":\"Cats have excellent night vision.\"}";

    ObjectMapper objectMapper = new ObjectMapper();
    CatFactDto catFactDto = objectMapper.readValue(json, CatFactDto.class);

    assertEquals(1L, catFactDto.getId());
    assertEquals("Cats have excellent night vision.", catFactDto.getFact());
  }

  @Test
  void testCatDtoSerialization() throws JsonProcessingException {
    CatDto catDto = new CatDto(1L, "Whiskers", 5);
    List<CatFactDto> facts = new ArrayList<>();
    facts.add(new CatFactDto(1L, "Cats have 30 teeth."));
    facts.add(new CatFactDto(2L, "Cats spend 70% of their life sleeping."));
    catDto.setFacts(facts);

    List<OwnerDto> owners = new ArrayList<>();
    owners.add(new OwnerDto(1L, "John"));
    owners.add(new OwnerDto(2L, "Jane"));
    catDto.setOwners(owners);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(catDto);

    assertTrue(json.contains("\"id\":1"));
    assertTrue(json.contains("\"name\":\"Whiskers\""));
    assertTrue(json.contains("\"age\":5"));
    assertTrue(json.contains("\"fact\":\"Cats have 30 teeth.\""));
    assertTrue(json.contains("\"fact\":\"Cats spend 70% of their life sleeping.\""));
    assertTrue(json.contains("\"name\":\"John\""));
    assertTrue(json.contains("\"name\":\"Jane\""));
  }



}
