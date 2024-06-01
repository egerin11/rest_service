package com.example.laba.controller;

import com.example.laba.model.Owner;
import com.example.laba.model.dto.OwnerDto;
import com.example.laba.service.OwnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** The type Owner and cat controller. */
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
@Tag(name = "Owner and Cat ", description = "API to manipulate  owner")
@CrossOrigin(origins = "http://localhost:4200")
public class OwnerAndCatController {

  private final OwnerService ownerService;

  /**
   * Gets owner.
   *
   * @param id the id
   * @return the owner
   */
  @GetMapping("/owner/{id}")
  public Owner getOwner(@PathVariable Long id) {
    return ownerService.getOwner(id);
  }

  /**
   * Gets owners.
   *
   * @return the owners
   */
  @GetMapping("/owners")
  public List<OwnerDto> getOwners() {
    return ownerService.getAllOwners();
  }

  /**
   * Add owner owner.
   *
   * @param owner the owner
   * @return the owner
   */
  @PostMapping("/add-owner")
  public Owner addOwner(@RequestBody Owner owner) {
    return ownerService.addOwner(owner);
  }

  /**
   * Update owner owner.
   *
   * @param id the id
   * @param owner the owner
   * @return the owner
   */
  @PutMapping("/update-owner/{id}")
  public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
    return ownerService.updateOwner(id, owner);
  }

  /**
   * Delete cat to owner owner.
   *
   * @param catId the cat id
   * @param ownerId the owner id
   * @return the owner
   */
  @DeleteMapping("/cat/{catId}/owner/{ownerId}")
  public OwnerDto deleteCatToOwner(@PathVariable Long catId, @PathVariable Long ownerId) {
    return ownerService.deleteCatToOwner(catId, ownerId);
  }

  /**
   * Delete owner string.
   *
   * @param id the id
   * @return the string
   */
  @DeleteMapping("/delete-owner/{id}")
  public String deleteOwner(@PathVariable Long id) {
    ownerService.removeOwner(id);
    return "delete";
  }
}
