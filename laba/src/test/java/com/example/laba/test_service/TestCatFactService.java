package com.example.laba.test_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.service.FactService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class TestCatFactService {
  @Mock private CatFactRepository listFactRepository;

  @Mock private CatRepositoryDao catRepositoryDao;
  @InjectMocks private FactService factService;

  @Test
  void testAddFact() {
    Long catId = 1L;
    CatFact catFact = new CatFact("Fact 1");

    Cat cat = new Cat();
    cat.setAge(5);
    cat.setId(1L);
    cat.setName("jorik");;

    when(catRepositoryDao.findById(catId)).thenReturn(Optional.of(cat));
    when(listFactRepository.save(catFact)).thenReturn(catFact);

    CatFact result = factService.addFact(catFact, catId);

    assertEquals(catFact, result);
    assertEquals(cat, catFact.getCat());
    verify(catRepositoryDao, times(1)).findById(catId);
    verify(listFactRepository, times(1)).save(catFact);
  }

  @Test
  void testGetFacts() {
    List<CatFact> facts = new ArrayList<>();
    facts.add(new CatFact("Fact 1"));
    facts.add(new CatFact("Fact 2"));

    when(listFactRepository.findAll()).thenReturn(facts);

    List<CatFact> result = factService.getFacts();

    assertEquals(facts, result);
    verify(listFactRepository, times(1)).findAll();
  }

  @Test
  void testGetFact() {
    Long factId = 1L;
    CatFact catFact = new CatFact("Fact 1");

    when(listFactRepository.findById(factId)).thenReturn(Optional.of(catFact));

    CatFact result = factService.getFact(factId);

    assertEquals(catFact, result);
    verify(listFactRepository, times(1)).findById(factId);
  }

  @Test
  void testGetFactNotFound() {
    Long factId = 1L;
    when(listFactRepository.findById(factId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> factService.getFact(factId));
    verify(listFactRepository, times(1)).findById(factId);
  }

  @Test
  void testRemoveFact() {
    Long factId = 1L;
    CatFact catFact = new CatFact("Fact 1");

    when(listFactRepository.findById(factId)).thenReturn(Optional.of(catFact));

    CatFact result = factService.removeFact(factId);

    assertEquals(catFact, result);
    verify(listFactRepository, times(1)).findById(factId);
    verify(listFactRepository, times(1)).delete(catFact);
  }

  @Test
  void testUpdateFact() {
    Long factId = 1L;
    CatFact oldFact = new CatFact("Fact 1");
    CatFact newFact = new CatFact("Fact 2");

    when(listFactRepository.findById(factId)).thenReturn(Optional.of(oldFact));
    when(listFactRepository.save(oldFact)).thenReturn(oldFact);

    CatFact result = factService.updateFact(factId, newFact);

    assertEquals(oldFact, result);
    assertEquals("Fact 2", oldFact.getFact());
    verify(listFactRepository, times(1)).findById(factId);
    verify(listFactRepository, times(1)).save(oldFact);
  }
}
