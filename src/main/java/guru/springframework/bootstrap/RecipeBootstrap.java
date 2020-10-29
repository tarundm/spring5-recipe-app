package guru.springframework.bootstrap;

import guru.springframework.models.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Catalina;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// THis will inject logger
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final CategoryRepository categoryRepository;

    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository
            categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Log: Adding bootstrap data");
    }

    private List<Recipe> getRecipes(){
        log.debug("Log: Entering into Get Recipes");
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOpt = unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasure> tableSpoonUomOpt = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> teaSpoonUomOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> dashUomOpt = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> pintUomOpt = unitOfMeasureRepository.findByDescription("Pint");
        Optional<UnitOfMeasure> cupsUomOpt = unitOfMeasureRepository.findByDescription("Cup");
        if(!eachUomOpt.isPresent() || !tableSpoonUomOpt.isPresent() || !teaSpoonUomOpt.isPresent() ||
                !dashUomOpt.isPresent() || !pintUomOpt.isPresent() || !cupsUomOpt.isPresent()){
            log.error("Expected UOM not found");
            throw new RuntimeException("Expected UOM not found");
        }

        UnitOfMeasure eachUom = eachUomOpt.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOpt.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOpt.get();
        UnitOfMeasure dashUom = dashUomOpt.get();
        UnitOfMeasure pintUom = pintUomOpt.get();
        UnitOfMeasure cupsUom = cupsUomOpt.get();

        Optional<Category> americanCategoryOpt = categoryRepository.findByDescription("American");
        Optional<Category> mexicanCategoryOpt = categoryRepository.findByDescription("Mexican");
        if(!americanCategoryOpt.isPresent() || !mexicanCategoryOpt.isPresent()){
            log.error("Expected Category not found");
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOpt.get();
        Category mexicanCategory = mexicanCategoryOpt.get();

        // Recipe1
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(20);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. " +
                        "Score the inside of the");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Be careful handling chiles if using.");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.setServings(5);
        guacRecipe.setSource("Mothers Kitchen");
        guacRecipe.setUrl("www.xyx.com");

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal("2"), eachUom));
        guacRecipe.addIngredient(new Ingredient("teaspoon of salt", new BigDecimal("2"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("lemon juice", new BigDecimal("2"), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("red onion", new BigDecimal("2"), eachUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles", new BigDecimal("1"), pintUom));
        guacRecipe.addIngredient(new Ingredient("black pepper", new BigDecimal("1"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("tomato", new BigDecimal("2"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);
        log.debug("Log: Added Guacamole Recipe");

        // Recipe2
        Recipe tacosRecipe = new Recipe();

        tacosRecipe.setDescription("Grilled Chicken Tacos");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("Your directions for grilling thighs requires a response, although");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("If you aren't in a grilling mood or don’t have a");
        tacosNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.setServings(5);
        tacosRecipe.setSource("Mothers Kitchen");
        tacosRecipe.setUrl("www.xyx.com");

        tacosRecipe.addIngredient(new Ingredient("chicken thighs", new BigDecimal("8"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(".5"), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("black pepper\n", new BigDecimal("1"), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient(" salsa verde", new BigDecimal("1.5"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Oil", new BigDecimal("2"), tableSpoonUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        log.debug("Log: Added Chicken Tacos Recipe");

        log.debug("Log: Exiting Get Recipes");
        return recipes;
    }
}
