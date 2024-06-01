package com.example.laba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** The type Owner. */
@Entity
public class Owner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "owners")
  private Set<Cat> cats = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Owner owner = (Owner) o;
    return Objects.equals(name, owner.name);
  }

  @Override
  public String toString() {
    return "Owner{" + "id=" + id + ", name='" + name + '\'' + ", cats=" + cats + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets cats.
   *
   * @return the cats
   */
  public Set<Cat> getCats() {
    return cats;
  }

  /**
   * Sets cats.
   *
   * @param cats the cats
   */
  public void setCats(Set<Cat> cats) {
    this.cats = cats;
  }
}
