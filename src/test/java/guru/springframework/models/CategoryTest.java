package guru.springframework.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// THis is pure JUnit test class

class CategoryTest {

    // @BeforeEach - replacement of @Before
    // @BeforeAll - replacement of @BeforeClass

    // Add a category property
    Category category;

    // Add a set up method where Category will be initialized
    // @BeforeEach is JUnit5 antn is replacement of @Before is JUnit4 antn
    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long idVal = 4L;
        category.setId(idVal);
        assertEquals(idVal, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}