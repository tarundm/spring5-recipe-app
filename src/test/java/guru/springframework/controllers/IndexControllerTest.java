package guru.springframework.controllers;

import guru.springframework.models.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
    void testMockMVC() throws Exception {
        // This is another way of testing controller request path mapping. Earlier we had bring entire web server to
        // test it but now we can mock web server but actually mock dispatcher servlet. This make test aroung controller
        // very very lightweight. MockMVC mock dispatcher servlet to run the test and does not initialize whole web
        // application context.

        // Brings in whole Web application context and it become full functional test
        // MockMvc mockMvcWeb = MockMvcBuilders.webAppContextSetup(??).build();

        // standalone setup is unit test and its fast
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe"));
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