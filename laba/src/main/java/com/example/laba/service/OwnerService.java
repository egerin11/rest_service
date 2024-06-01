package com.example.laba.service;

import com.example.laba.model.Cat;
import com.example.laba.model.Owner;
import com.example.laba.model.dto.OwnerDto;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.interfaces.OwnerInterface;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

/** The type Owner service. */
@Service
@Hidden
public class OwnerService implements OwnerInterface {
  /** ownerRepository is a repository of entity owner. */
  private final OwnerRepository ownerRepository;

  private final ModelMapper mapper;
  private final CatRepositoryDao catRepositoryDao;

  /**
   * Instantiates a new Owner service.
   *
   * @param ownerRepository the owner repository
   * @param catRepositoryDao the cat repository dao
   */
  @Autowired
  public OwnerService(
      OwnerRepository ownerRepository, ModelMapper mapper, CatRepositoryDao catRepositoryDao) {
    this.ownerRepository = ownerRepository;
    this.mapper = mapper;
    this.catRepositoryDao = catRepositoryDao;
  }

  @Override
  public List<OwnerDto> getAllOwners() {
    return StreamSupport.stream(ownerRepository.findAll().spliterator(), false)
        .map(owner -> mapper.map(owner, OwnerDto.class))
        .toList();
  }

  @Override
  public Owner addOwner(@NotNull Owner owner) {
    if (owner.getCats() != null) {
      Set<Cat> savedCats = new HashSet<>();
      for (Cat cat : owner.getCats()) {
        if (cat.getId() == null) {
          cat = catRepositoryDao.save(cat);
        }
        savedCats.add(cat);
      } 
      for (Cat savedCat : savedCats) {
        savedCat.addOwner(owner);
      }
      owner.setCats(savedCats);
    }
    return ownerRepository.save(owner);
  }

  @Override
  public Owner getOwner(Long id) {
    return ownerRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("owner not found"));
  }

  @Override
  public Owner removeOwner(Long id) {
    Owner owner = getOwner(id);
    ownerRepository.delete(owner);
    return owner;
  }

  @Override
  public Owner updateOwner(Long id, @NotNull Owner owner) {
    Owner existingOwner = getOwner(id);
    existingOwner.setName(owner.getName());
    return ownerRepository.save(existingOwner);
  }

  @Override
  public OwnerDto deleteCatToOwner(Long catId, Long ownerId) {
    Cat cat =
        catRepositoryDao
            .findById(catId)
            .orElseThrow(() -> new ResourceNotFoundException("Cat not found"));
    Owner owner =
        ownerRepository
            .findById(ownerId)
            .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));
    cat.removeOwner(owner);
    catRepositoryDao.save(cat);
    ownerRepository.save(owner);
    return mapper.map(owner,OwnerDto.class);
  }
}
