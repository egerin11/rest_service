package com.example.laba.test_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.Owner;
import com.example.laba.model.dto.CatDto;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.CatService;
import com.example.laba.service.FactService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class TestCatService {
  @Mock private CatRepositoryDao catRepositoryDao;
  @Mock private CatFactRepository catFactRepository;
  @Mock private FactService factService;
  @Mock private OwnerRepository ownerRepository;
  @Mock private ModelMapper mapper;
  @InjectMocks private CatService catService;

  @Test
  void testGetCat() {
    Cat cat = new Cat();
    cat.setAge(5);
    cat.setId(1L);
    cat.setName("jorik");

    CatDto expectedCatDto = new CatDto();
    expectedCatDto.setAge(5);
    expectedCatDto.setId(1L);
    expectedCatDto.setName("jorik");

    when(catRepositoryDao.findById(1L)).thenReturn(Optional.of(cat));
    when(mapper.map(cat, CatDto.class)).thenReturn(expectedCatDto);

    CatDto testCatDto = catService.getCat(1L);

    assertNotNull(testCatDto);
    assertEquals(expectedCatDto, testCatDto);
  }

  @Test
  void testGetAllCat() {
    Cat cat1 = new Cat();
    cat1.setName("Fluffy");
    Cat cat2 = new Cat();
    cat2.setName("Whiskers");
    List<Cat> catList = Arrays.asList(cat1, cat2);

    when(catRepositoryDao.findAll()).thenReturn(catList);
    when(mapper.map(cat1, CatDto.class)).thenReturn(new CatDto("Fluffy"));
    when(mapper.map(cat2, CatDto.class)).thenReturn(new CatDto("Whiskers"));

    List<CatDto> result = catService.getAllCat();
    assertEquals(2, result.size());
    assertEquals("Fluffy", result.get(0).getName());
    assertEquals("Whiskers", result.get(1).getName());
  }

//  @Test
//  void testRemoveCat() {
//    catService.removeCat(1L);
//    verify(catRepositoryDao).deleteById(1L);
//    assertEquals("delete", catService.removeCat(1L));
//  }

  @Test
  void testFindCatsByOwnerId() {
    Cat cat1 = new Cat();
    cat1.setName("Fluffy");
    Cat cat2 = new Cat();
    cat2.setName("Whiskers");
    List<Cat> catList = Arrays.asList(cat1, cat2);

    when(catRepositoryDao.findCatsByOwnerId(1L)).thenReturn(catList);
    when(mapper.map(cat1, CatDto.class)).thenReturn(new CatDto("Fluffy"));
    when(mapper.map(cat2, CatDto.class)).thenReturn(new CatDto("Whiskers"));

    List<CatDto> result = catService.findCatsByOwnerId(1L);

    assertEquals(2, result.size());
    assertEquals("Fluffy", result.get(0).getName());
    assertEquals("Whiskers", result.get(1).getName());
  }
  @Test
  void testAddCat() {
    Cat cat = new Cat();
    cat.setName("Fluffy");
    cat.setAge(3);
    CatFact catFact = new CatFact();
    catFact.setFact("Cats have 30 teeth.");
    cat.getFacts().add(catFact);
    Owner owner = new Owner();
    owner.setName("John");
    cat.getOwners().add(owner);

    Cat savedCat = new Cat();
    savedCat.setId(1L);
    savedCat.setName("Fluffy");
    savedCat.setAge(3);

    CatDto expectedCatDto = new CatDto();
    expectedCatDto.setId(1L);
    expectedCatDto.setName("Fluffy");
    expectedCatDto.setAge(3);

    when(catRepositoryDao.save(any(Cat.class))).thenReturn(savedCat);
    when(catFactRepository.save(any(CatFact.class))).thenReturn(catFact);
    when(ownerRepository.findByName("John")).thenReturn(null);
    when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
    when(mapper.map(cat, CatDto.class)).thenReturn(expectedCatDto);

    CatDto result = catService.addCat(cat);

    assertNotNull(result);
    assertEquals(expectedCatDto, result);
    verify(catRepositoryDao, times(2)).save(any(Cat.class));
    verify(catFactRepository).save(any(CatFact.class));
    verify(ownerRepository).findByName("John");
    verify(ownerRepository).save(any(Owner.class));
  }

  @Test
  void testAddList() {
    Cat cat1 = new Cat();
    cat1.setName("Fluffy");
    cat1.setAge(3);
    CatFact fact1 = new CatFact();
    fact1.setFact("Cats have 30 teeth.");
    cat1.getFacts().add(fact1);
    Owner owner1 = new Owner();
    owner1.setName("John");
    cat1.getOwners().add(owner1);

    Cat cat2 = new Cat();
    cat2.setName("Whiskers");
    cat2.setAge(5);
    CatFact fact2 = new CatFact();
    fact2.setFact("Cats are obligate carnivores.");
    cat2.getFacts().add(fact2);
    Owner owner2 = new Owner();
    owner2.setName("Jane");
    cat2.getOwners().add(owner2);

    List<Cat> cats = Arrays.asList(cat1, cat2);

    Cat savedCat1 = new Cat();
    savedCat1.setId(1L);
    savedCat1.setName("Fluffy");
    savedCat1.setAge(3);
    savedCat1.getFacts().add(fact1);
    savedCat1.getOwners().add(owner1);

    Cat savedCat2 = new Cat();
    savedCat2.setId(2L);
    savedCat2.setName("Whiskers");
    savedCat2.setAge(5);
    savedCat2.getFacts().add(fact2);
    savedCat2.getOwners().add(owner2);

    CatDto expectedCatDto1 = new CatDto();
    expectedCatDto1.setId(1L);
    expectedCatDto1.setName("Fluffy");
    expectedCatDto1.setAge(3);

    CatDto expectedCatDto2 = new CatDto();
    expectedCatDto2.setId(2L);
    expectedCatDto2.setName("Whiskers");
    expectedCatDto2.setAge(5);

    when(catRepositoryDao.save(any(Cat.class))).thenReturn(savedCat1, savedCat2);
    when(catFactRepository.save(any(CatFact.class))).thenReturn(fact1, fact2);
    when(ownerRepository.findByName(any(String.class))).thenReturn(null, null);
    when(ownerRepository.save(any(Owner.class))).thenReturn(owner1, owner2);
    when(mapper.map(cat1, CatDto.class)).thenReturn(expectedCatDto1);
    when(mapper.map(cat2, CatDto.class)).thenReturn(expectedCatDto2);

    List<CatDto> result = catService.addList(cats);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(expectedCatDto1, result.get(0));
    assertEquals(expectedCatDto2, result.get(1));
    verify(catRepositoryDao, times(4)).save(any(Cat.class));
    verify(catFactRepository, times(2)).save(any(CatFact.class));
    verify(ownerRepository, times(2)).findByName(any(String.class));
    verify(ownerRepository, times(2)).save(any(Owner.class));
  }


  @Test
  void testUpdateCat() {
    Long id = 1L;
    Cat updatedCat = new Cat();
    updatedCat.setName("Fluffy");
    updatedCat.setAge(5);
    updatedCat.getFacts().add(new CatFact());
    updatedCat.getOwners().add(new Owner());

    Cat existingCat = new Cat();
    existingCat.setId(id);
    existingCat.setName("Whiskers");
    existingCat.setAge(3);

    CatDto expectedCatDto = new CatDto();
    expectedCatDto.setId(id);
    expectedCatDto.setName("Fluffy");
    expectedCatDto.setAge(5);

    when(catRepositoryDao.findById(id)).thenReturn(Optional.of(existingCat));
    when(catRepositoryDao.save(any(Cat.class))).thenReturn(existingCat);
    when(mapper.map(existingCat, CatDto.class)).thenReturn(expectedCatDto);


    CatDto result = catService.updateCat(id, updatedCat);

    assertNotNull(result);
    assertEquals(expectedCatDto, result);
    verify(catRepositoryDao).findById(id);
    verify(catRepositoryDao).save(any(Cat.class));
  }


}
