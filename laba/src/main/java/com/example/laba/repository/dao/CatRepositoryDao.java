package com.example.laba.repository.dao;

import com.example.laba.model.Cat;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** The interface Cat repository dao. */
@Repository
@Hidden
public interface CatRepositoryDao extends JpaRepository<Cat, Long> {
  /**
   * Find cats by owner id list.
   *
   * @param id the id
   * @return the list
   */
  @Transactional
  @Query("SELECT c FROM Cat c JOIN c.owners o WHERE o.id = :id")
  List<Cat> findCatsByOwnerId(@Param("id") Long id);
}
