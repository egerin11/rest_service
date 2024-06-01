package com.example.laba.test_controler;

import com.example.laba.controller.CatAndCatFactController;
import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.dto.CatDto;
import com.example.laba.service.CatService;
import com.example.laba.service.FactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatAndCatFactControllerTest {

  @Mock private FactService factService;

  @Mock private CatService catService;

  @InjectMocks private CatAndCatFactController controller;

  @Test
  void testAddFact() {
    CatFact catFact = new CatFact();
    Long id = 1L;
    when(factService.addFact(catFact, id)).thenReturn(catFact);

    CatFact result = controller.addFact(catFact, id);

    assertEquals(catFact, result);
    verify(factService).addFact(catFact, id);
  }

  @Test
  void testAddList() {
    List<Cat> cats = new ArrayList<>();
    List<CatDto> catDtos = new ArrayList<>();
    when(catService.addList(cats)).thenReturn(catDtos);

    List<CatDto> result = controller.addList(cats);

    assertEquals(catDtos, result);
    verify(catService).addList(cats);
  }

  @Test
  void testAddToMyList() {
    Cat cat = new Cat();
    CatDto catDto = new CatDto();
    when(catService.addCat(cat)).thenReturn(catDto);

    CatDto result = controller.addToMyList(cat);

    assertEquals(catDto, result);
    verify(catService).addCat(cat);
  }

  @Test
  void testAddFactToCat() {
    Long factId = 1L;
    Long catId = 2L;
    Cat cat = new Cat();
    when(catService.addFactToCat(factId, catId)).thenReturn(cat);

    Cat result = controller.addFactToCat(factId, catId);

    assertEquals(cat, result);
    verify(catService).addFactToCat(factId, catId);
  }

  @Test
  void testFindCatsByOwnerId() {
    Long id = 1L;
    List<CatDto> catDtos = new ArrayList<>();
    when(catService.findCatsByOwnerId(id)).thenReturn(catDtos);

    List<CatDto> result = controller.findCatsByOwnerId(id);

    assertEquals(catDtos, result);
    verify(catService).findCatsByOwnerId(id);
  }

  @Test
  void testGetCats() {
    List<CatDto> catDtos = new ArrayList<>();
    when(catService.getAllCat()).thenReturn(catDtos);

    List<CatDto> result = controller.getCats();

    assertEquals(catDtos, result);
    verify(catService).getAllCat();
  }

  @Test
  void testGetCatById() {
    Long id = 1L;
    CatDto catDto = new CatDto();
    when(catService.getCat(id)).thenReturn(catDto);

    CatDto result = controller.getCatById(id);

    assertEquals(catDto, result);
    verify(catService).getCat(id);
  }

  @Test
  void testGetFact() {
    Long id = 1L;
    CatFact catFact = new CatFact();
    when(factService.getFact(id)).thenReturn(catFact);

    CatFact result = controller.getFact(id);

    assertEquals(catFact, result);
    verify(factService).getFact(id);
  }

  @Test
  void testGetFacts() {
    List<CatFact> catFacts = new ArrayList<>();
    when(factService.getFacts()).thenReturn(catFacts);

    List<CatFact> result = controller.getFacts();

    assertEquals(catFacts, result);
    verify(factService).getFacts();
  }

  @Test
  void testUpdateFact() {
    Long id = 1L;
    CatFact catFact = new CatFact();
    when(factService.updateFact(id, catFact)).thenReturn(catFact);

    CatFact result = controller.updateFact(id, catFact);

    assertEquals(catFact, result);
    verify(factService).updateFact(id, catFact);
  }

  @Test
  void testUpdateCat() {
    Long id = 1L;
    Cat cat = new Cat();
    CatDto catDto = new CatDto();
    when(catService.updateCat(id, cat)).thenReturn(catDto);

    CatDto result = controller.updateCat(id, cat);

    assertEquals(catDto, result);
    verify(catService).updateCat(id, cat);
  }

  @Test
  void testRemoveFact() {
    Long id = 1L;
    CatFact catFact = new CatFact();
    when(factService.removeFact(id)).thenReturn(catFact);

    CatFact result = controller.remove(id);

    assertEquals(catFact, result);
    verify(factService).removeFact(id);
  }

//  @Test
//  void testDeleteCat() {
//    Long id = 1L;
//    String message = "Cat deleted";
//    when(catService.removeCat(id)).thenReturn(message);
//
//    String result = controller.deleteCat(id);
//
//    assertEquals(message, result);
//    verify(catService).removeCat(id);
//  }
}
