package com.example.laba.controller;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.dto.CatDto;
import com.example.laba.service.CatService;
import com.example.laba.service.FactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.web.bind.annotation.*;

/** The type Cat and cat fact controller. */
@RestController
@RequestMapping("/list")
@Tag(name = "Cat and Fact", description = "API to manipulate  cat and fact")
@CrossOrigin(origins = "http://localhost:4200")
public class CatAndCatFactController {

  private final FactService factService;
  private final CatService catService;

  /**
   * Instantiates a new Cat and cat fact controller.
   *
   * @param factService the fact service
   * @param catService the cat service
   */
  public CatAndCatFactController(FactService factService, CatService catService) {
    this.factService = factService;
    this.catService = catService;
  }

  /**
   * Add fact cat fact.
   *
   * @param catFact the cat fact
   * @param id the id
   * @return the cat fact
   */
  @PostMapping("/add-fact/{id}")
  @Operation(summary = "updates fact by id") // 2
  public CatFact addFact(@RequestBody CatFact catFact, @PathVariable Long id) {
    return factService.addFact(catFact, id);
  }

  @PostMapping("/add-list")
  public List<CatDto> addList(@RequestBody List<Cat> cats) {
    return catService.addList(cats);
  }

  /**
   * Add to my list cat dto.
   *
   * @param cat the cat
   * @return the cat dto
   */
  @PostMapping("/add-cat")
  @Operation(summary = "updates fact by id") // 2
  public CatDto addToMyList(@RequestBody Cat cat) {
    return catService.addCat(cat);
  }

  /**
   * Add fact to cat cat.
   *
   * @param factId the fact id
   * @param catId the cat id
   * @return the cat
   */
  @PostMapping("/fact/{factId}/cat/{catId}")
  @Operation(summary = "updates fact by id") // 2
  public Cat addFactToCat(@PathVariable Long factId, @PathVariable Long catId) {
    return catService.addFactToCat(factId, catId);
  }

  /**
   * Find cats by owner id list.
   *
   * @param id the id
   * @return the list
   */
  @GetMapping("/find-cat-by-owner/{id}")
  @Operation(summary = "updates fact by id") // 2
  public List<CatDto> findCatsByOwnerId(@PathVariable Long id) {
    return catService.findCatsByOwnerId(id);
  }

  /**
   * Gets cats.
   *
   * @return the cats
   */
  @GetMapping("/cats")
  @Operation(summary = "updates fact by id") // 2
  public List<CatDto> getCats() {
    return catService.getAllCat();
  }

  /**
   * Gets cat by id.
   *
   * @param id the id
   * @return the cat by id
   */
    @GetMapping("/cat/{id}")
    @Operation(summary = "updates fact by id") // 2
    public CatDto getCatById(@PathVariable Long id) {
      return catService.getCat(id);
    }

  /**
   * Gets fact.
   *
   * @param id the id
   * @return the fact
   */
  @GetMapping("/fact/{id}")
  public CatFact getFact(@PathVariable Long id) {
    return factService.getFact(id);
  }

  /**
   * Gets facts.
   *
   * @return the facts
   */
  @GetMapping("/get-all-fact")
  @Operation(summary = "updates fact by id") // 2
  public List<CatFact> getFacts() {
    return factService.getFacts();
  }

  /**
   * Update fact cat fact.
   *
   * @param id the id
   * @param catFact the cat fact
   * @return the cat fact
   */
  @PutMapping("/update-fact/{id}")
  @Operation(summary = "updates fact by id") // 2
  public CatFact updateFact(@PathVariable Long id, @RequestBody CatFact catFact) {
    return factService.updateFact(id, catFact);
  }

  /**
   * Update cat cat dto.
   *
   * @param id the id
   * @param cat the cat
   * @return the cat dto
   */
  @PutMapping("/update-cat/{id}")
  @Operation(summary = "updates fact by id") // 2
  public CatDto updateCat(@PathVariable Long id, @RequestBody Cat cat) {
    return catService.updateCat(id, cat);
  }

  /**
   * Remove cat fact.
   *
   * @param id the id
   * @return the cat fact
   */
  @DeleteMapping("/delete-fact/{id}")
  @Operation(summary = "updates fact by id") // 2
  public CatFact remove(@PathVariable Long id) {
    return factService.removeFact(id);
  }

  /**
   * Delete cat string.
   *
   * @param id the id
   * @return the string
   */
  @DeleteMapping("/delete-cat/{id}")
  @Operation(summary = "updates fact by id") // 2
  public CatDto deleteCat(@PathVariable Long id) {
    return catService.removeCat(id);
  }
}
