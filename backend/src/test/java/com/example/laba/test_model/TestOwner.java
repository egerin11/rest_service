package com.example.laba.test_model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.laba.model.Cat;
import com.example.laba.model.Owner;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerTest {
  @Mock private Set<Cat> cat;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testOwnerCreation() {
    Owner owner = new Owner();
    owner.setName("irinochka");
    assertEquals("irinochka", owner.getName());
  }

  @Test
  void testOwnerAddCat() {
      Owner owner = new Owner();
      owner.setName("irinochka");
      owner.setCats(cat);
      assertEquals(cat,owner.getCats());

  }
}
