package com.example.laba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

/** The type Cat fact. */
@Entity
public class CatFact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fact;

  @ManyToOne
  @JoinColumn(name = "cat_id")
  @JsonIgnore
  private Cat cat;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatFact catFact = (CatFact) o;
    return Objects.equals(fact, catFact.fact);
  }

  @Override
  public String toString() {
    return "CatFact{" + "id=" + id + ", fact='" + fact + '\'' + ", cat=" + cat + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(fact);
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
   * Gets fact.
   *
   * @return the fact
   */
  public String getFact() {
    return fact;
  }

  /**
   * Sets fact.
   *
   * @param fact the fact
   */
  public void setFact(String fact) {
    this.fact = fact;
  }

  /** Instantiates a new Cat fact. */
  public CatFact() {}

  /**
   * Instantiates a new Cat fact.
   *
   * @param fact the fact
   */
  public CatFact(String fact) {
    this.fact = fact;
  }

  /**
   * Gets cat.
   *
   * @return the cat
   */
  public Cat getCat() {
    return cat;
  }

  /**
   * Sets cat.
   *
   * @param cat the cat
   */
  public void setCat(Cat cat) {
    this.cat = cat;
  }
}
