package guru.springframework.controllers;

import guru.springframework.models.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe2 = new Recipe();
        recipe2.setId(1L);
        recipes.add(recipe2);

        // given
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String viewName = indexController.getIndexPage(model);

        // then
        System.out.println("assert View Name");
        assertEquals("recipe", viewName);

        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();

        Mockito.verify(model, Mockito.times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        // By Tarun - confirm later
        // Mockito.verify(model, Mockito.times(1)).addAttribute("recipes", recipes);
        Set<Recipe> setInController = argumentCaptor.getValue();
        System.out.println("assert recipe set");
        assertEquals(2, setInController.size());

    }
}