package guru.springframework.controllers;

import guru.springframework.models.Recipe;
import guru.springframework.models.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Log: Getting Index Page");
        Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        return "recipe";
    }
}