package com.example.laba.service.interfaces;

import com.example.laba.model.Owner;
import com.example.laba.model.dto.OwnerDto;
import java.util.List;

/** The interface Owner interface. */
public interface OwnerInterface {

  /**
   * Gets owner.
   *
   * @param id the id
   * @return the owner
   */
  Owner getOwner(Long id);

  /**
   * Add owner owner.
   *
   * @param owner the owner
   * @return the owner
   */
  Owner addOwner(Owner owner);

  /**
   * Gets all owners.
   *
   * @return the all owners
   */
  List<OwnerDto> getAllOwners();

  /**
   * Remove owner owner.
   *
   * @param id the id
   * @return the owner
   */
  Owner removeOwner(Long id);

  /**
   * Update owner owner.
   *
   * @param id the id
   * @param owner the owner
   * @return the owner
   */
  Owner updateOwner(Long id, Owner owner);

  /**
   * Delete cat to owner owner.
   *
   * @param catId the cat id
   * @param ownerId the owner id
   * @return the owner
   */
  OwnerDto deleteCatToOwner(Long catId, Long ownerId);
}
