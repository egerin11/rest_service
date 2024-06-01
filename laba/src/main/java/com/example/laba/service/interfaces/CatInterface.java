package com.example.laba.service.interfaces;

import com.example.laba.model.Cat;
import com.example.laba.model.dto.CatDto;
import java.util.List;

/** The interface Cat interface. */
public interface CatInterface {
  /**
   * Gets cat.
   *
   * @param id the id
   * @return the cat
   */
  CatDto getCat(Long id);

  /**
   * Add cat cat dto.
   *
   * @param cat the cat
   * @return the cat dto
   */
  CatDto addCat(Cat cat);

  /**
   * Remove cat string.
   *
   * @param id the id
   * @return the string
   */
  CatDto removeCat(Long id);

  /**
   * Update cat cat dto.
   *
   * @param id the id
   * @param cat the cat
   * @return the cat dto
   */
  CatDto updateCat(Long id, Cat cat);

  /**
   * Gets all cat.
   *
   * @return the all cat
   */
  List<CatDto> getAllCat();

  /**
   * Add fact to cat cat.
   *
   * @param factId the fact id
   * @param catId the cat id
   * @return the cat
   */
  Cat addFactToCat(Long factId, Long catId);

  /**
   * Find cats by owner id list.
   *
   * @param id the id
   * @return the list
   */
  List<CatDto> findCatsByOwnerId(Long id);

  /**
   * Add list list.
   *
   * @param cats the cats
   * @return the list
   */
  List<CatDto> addList(List<Cat> cats);
}
