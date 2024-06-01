package com.example.laba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** The type Cat. */
@Entity
public class Cat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Integer age;

  @OneToMany(mappedBy = "cat")
  private Set<CatFact> facts = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "cat_owner",
      joinColumns = @JoinColumn(name = "cat_id"),
      inverseJoinColumns = @JoinColumn(name = "owner_id"))
  private Set<Owner> owners = new HashSet<>();

  public void addOwner(Owner owner) {
    owners.add(owner);
    owner.getCats().add(this);
  }

  public void removeOwner(Owner owner) {
    owners.remove(owner);
    owner.getCats().remove(this);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cat cat = (Cat) o;
    return Objects.equals(name, cat.name) && Objects.equals(age, cat.age);
  }

  @Override
  public String toString() {
    return "Cat{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", age="
        + age
        + ", facts="
        + facts
        + ", owners="
        + owners
        + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Set<CatFact> getFacts() {
    return facts;
  }

  public void setFacts(Set<CatFact> facts) {
    this.facts = facts;
  }

  public Set<Owner> getOwners() {
    return owners;
  }

  public void setOwners(Set<Owner> owners) {
    this.owners = owners;
  }
}
