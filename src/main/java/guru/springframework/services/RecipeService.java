package guru.springframework.services;

import guru.springframework.models.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();
}
