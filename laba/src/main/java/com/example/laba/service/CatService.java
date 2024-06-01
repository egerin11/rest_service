package com.example.laba.service;

import com.example.laba.cache.LruCache;
import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.Owner;
import com.example.laba.model.dto.CatDto;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.interfaces.CatInterface;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

/** The type Cat service. */
@Service
@Hidden
public class CatService implements CatInterface {

  private final CatRepositoryDao catRepositoryDao;
  private final CatFactRepository catFactRepository;
  private final FactService factService;
  private final OwnerRepository ownerRepository;
  private final ModelMapper mapper;

  private final LruCache<Long, CatDto> catCache = new LruCache<>(2);

  /**
   * Instantiates a new Cat service.
   *
   * @param catRepositoryDao the cat interface
   * @param catFactRepository the cat fact repository
   * @param factService the fact service
   * @param ownerRepository the owner repository
   * @param mapper the mapper
   */
  @Autowired
  public CatService(
      CatRepositoryDao catRepositoryDao,
      CatFactRepository catFactRepository,
      FactService factService,
      OwnerRepository ownerRepository,
      ModelMapper mapper) {
    this.catRepositoryDao = catRepositoryDao;
    this.catFactRepository = catFactRepository;
    this.factService = factService;
    this.ownerRepository = ownerRepository;
    this.mapper = mapper;
  }

  @Override
  public CatDto addCat(Cat cat) {

    Cat newCat = new Cat();
    newCat.setName(cat.getName());
    newCat.setAge(cat.getAge());

    Cat savedCat = catRepositoryDao.save(newCat);

    if (cat.getFacts() != null) {
      for (CatFact fact : cat.getFacts()) {
        if (!savedCat.getFacts().contains(fact)) {
          fact.setCat(savedCat);
          fact.setFact(fact.getFact());
          savedCat.getFacts().add(fact); // Add this line
          catFactRepository.save(fact);
        }
      }
    }
    if (cat.getOwners() != null) {
      for (Owner owner : cat.getOwners()) {
        Owner existingOwner = ownerRepository.findByName(owner.getName());
        if (existingOwner == null) {
          existingOwner = ownerRepository.save(owner);
        }
        savedCat.addOwner(existingOwner);
      }
      catRepositoryDao.save(savedCat);
    }

    return mapper.map(savedCat, CatDto.class);
  }

  @Override
  public List<CatDto> addList(List<Cat> cats) {
    return cats.stream()
        .map(
            cat -> {
              Cat newCat = new Cat();
              newCat.setName(cat.getName());
              newCat.setAge(cat.getAge());
              Cat savedCat = catRepositoryDao.save(newCat);

              if (cat.getFacts() != null) {
                cat.getFacts()
                    .forEach(
                        fact -> {
                          fact.setCat(savedCat);
                          catFactRepository.save(fact);
                        });
              }

              if (cat.getOwners() != null) {
                cat.getOwners()
                    .forEach(
                        owner -> {
                          Owner existingOwner = ownerRepository.findByName(owner.getName());
                          if (existingOwner == null) {
                            existingOwner = ownerRepository.save(owner);
                          }
                          savedCat.addOwner(existingOwner);
                        });
                catRepositoryDao.save(savedCat);
              }

              return mapper.map(cat, CatDto.class);
            })
        .toList();
  }

  @Override
  public CatDto getCat(Long id) {
    CatDto catDto = catCache.get(id);
    if (catDto == null) {
      catDto = Optional.ofNullable(catCache.get(id))
              .orElseGet(() -> {
                Cat cat = catRepositoryDao.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Cat not found"));
               CatDto catDto1 = mapper.map(cat, CatDto.class);
                catCache.put(id, catDto1);
                return catDto1;
              });
    }
    return catDto;
  }


  @Override
  public List<CatDto> getAllCat() {
    return StreamSupport.stream(catRepositoryDao.findAll().spliterator(), false)
        .map(cat -> mapper.map(cat, CatDto.class))
        .toList();
  }

  @Override
  public CatDto removeCat(Long id) {
    CatDto catDto=getCat(id);
    CatDto catDto1=catDto;
    catCache.remove(id);
    catRepositoryDao.delete(mapper.map(catDto,Cat.class));
    return catDto1;
  }

  @Override
  public CatDto updateCat(Long id, @NotNull Cat cat) {
    if (cat.getName() == null || cat.getAge() == null) {
      throw new IllegalArgumentException("Invalid cat data");
    }

    Cat existingCat =
        catRepositoryDao
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cat not found"));

    existingCat.setName(cat.getName());
    existingCat.setAge(cat.getAge());

    if (cat.getOwners() != null) {
      for (Owner owner : cat.getOwners()) {
        if (!existingCat.getOwners().contains(owner)) {
          existingCat.addOwner(owner);
          ownerRepository.save(owner);
        }
      }
      catRepositoryDao.save(existingCat);
    }

    if (cat.getFacts() != null) {
      for (CatFact fact : cat.getFacts()) {
        if (!existingCat.getFacts().contains(fact)) {
          fact.setCat(existingCat);
          fact.setFact(fact.getFact());
          existingCat.getFacts().add(fact);
          catFactRepository.save(fact);
        }
      }
    }
    CatDto catDto = mapper.map(existingCat, CatDto.class);
    catCache.put(id, catDto);
    return catDto;
  }

  @Override
  public Cat addFactToCat(Long factId, Long catId) {
    CatDto catDto = getCat(catId);
    Cat cat = mapper.map(catDto, Cat.class);
    CatFact fact = factService.getFact(factId);
    if (!cat.getFacts().contains(fact)) {
      cat.getFacts().add(fact);
      fact.setCat(cat);
      catRepositoryDao.save(cat);
      catFactRepository.save(fact);
      catCache.put(catId, mapper.map(cat, CatDto.class));
    }
    return cat;
  }

  @Override
  public List<CatDto> findCatsByOwnerId(Long id) {
    return StreamSupport.stream(catRepositoryDao.findCatsByOwnerId(id).spliterator(), false)
        .map(cat -> mapper.map(cat, CatDto.class))
        .toList();
  }
}
