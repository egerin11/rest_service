package com.example.laba.test_model;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@ExtendWith(MockitoExtension.class)
class CatFactTest {
    @Mock
    private Cat cat;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCatFactCreation() {
        CatFact catFact = new CatFact("This is a cat fact.");

        assertEquals("This is a cat fact.", catFact.getFact());
        assertNull(catFact.getCat());
    }

    @Test
    void testCatFactSetCat() {
        CatFact catFact = new CatFact();
        catFact.setFact("This is a cat fact.");
        catFact.setCat(cat);

        assertEquals(cat, catFact.getCat());
    }
}
