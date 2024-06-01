package com.example.laba.repository.dao;

import com.example.laba.model.CatFact;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Cat fact repository.
 */
@Repository
@Hidden
public interface CatFactRepository extends JpaRepository<CatFact, Long> {
  /**
   * Find by fact cat fact.
   *
   * @param fact the fact
   * @return the cat fact
   */
  CatFact findByFact(String fact);
}
