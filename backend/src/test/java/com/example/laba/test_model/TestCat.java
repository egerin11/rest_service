package com.example.laba.test_model;

import com.example.laba.model.Cat;
import com.example.laba.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {
  @Mock private Owner owner;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddOwner() {
    Cat cat = new Cat();
    cat.addOwner(owner);

    verify(owner, times(1)).getCats();
    assertTrue(cat.getOwners().contains(owner));
  }

  @Test
  void testRemoveOwner() {
    Cat cat = new Cat();
    cat.addOwner(owner);
    cat.removeOwner(owner);

    verify(owner, times(2)).getCats();
    assertTrue(cat.getOwners().isEmpty());
  }
}
