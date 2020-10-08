package guru.springframework.services;

import guru.springframework.models.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp(){
        System.out.println("Calling SetUp method");
        // THis is required to initialize RecipeRepository mock object
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        System.out.println("Calling getRecipes method");
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        assertEquals(1, recipeService.getRecipes().size());

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}