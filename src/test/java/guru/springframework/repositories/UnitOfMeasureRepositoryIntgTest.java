package guru.springframework.repositories;

import guru.springframework.models.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Integration test run to Spring context and load it
// @DataJpaTest will bring embedded databased and configure Spring Data JPA
// Here we are testing DB with test so this becomes Integration test
// We could use @SpringBooTest but we are going with @DataJpaTest
// When test of this class is run whole Spring context is brought in unlike Unit Test.
// If there are multiple test then first test will take time while others will use same spring app context.
@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIntgTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uom.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", uom.get().getDescription());
    }
}