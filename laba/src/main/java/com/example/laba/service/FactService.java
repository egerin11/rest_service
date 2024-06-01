package com.example.laba.service;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import java.util.stream.StreamSupport;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

/** The type Fact service. */
@Service
@Hidden
public class FactService {

  private final CatFactRepository listFactRepository;
  private final CatRepositoryDao catRepositoryDao;

  /**
   * Instantiates a new Fact service.
   *
   * @param listFactRepository the list fact repository
   * @param catRepositoryDao the cat repository dao
   */
  @Autowired
  public FactService(CatFactRepository listFactRepository, CatRepositoryDao catRepositoryDao) {
    this.listFactRepository = listFactRepository;
    this.catRepositoryDao = catRepositoryDao;
  }

  /**
   * Add fact cat fact.
   *
   * @param catFact the cat fact
   * @param id the id
   * @return the cat fact
   */
  public CatFact addFact(@NotNull CatFact catFact, Long id) {
    Cat cat = catRepositoryDao.findById(id).orElse(null);
    catFact.setCat(cat);
    return listFactRepository.save(catFact);
  }

  /**
   * Gets facts.
   *
   * @return the facts
   */
  public List<CatFact> getFacts() {
    return StreamSupport.stream(listFactRepository.findAll().spliterator(), false).toList();
  }

  /**
   * Gets fact.
   *
   * @param id the id
   * @return the fact
   */
  public CatFact getFact(Long id) {
    return listFactRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cat not found with id :" + id));
  }

  /**
   * Remove fact cat fact.
   *
   * @param id the id
   * @return the cat fact
   */
  public CatFact removeFact(Long id) {
    CatFact catFact = getFact(id);
    listFactRepository.delete(catFact);
    return catFact;
  }

  /**
   * Update fact cat fact.
   *
   * @param id the id
   * @param catFact the cat fact
   * @return the cat fact
   */
  public CatFact updateFact(Long id, @NotNull CatFact catFact) {
    CatFact oldItem = getFact(id);
    oldItem.setFact(catFact.getFact());
    listFactRepository.save(oldItem);
    return oldItem;
  }
}
