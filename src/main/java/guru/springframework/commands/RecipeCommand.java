package guru.springframework.commands;

import guru.springframework.models.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Added new class by tdm on 29th Oct 2020.
 * Command objects are POJOs that will be used by POST and GET requests.
 */
@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String directions;
    private String url;

    // Using IngredientCommand instead of Ingredient - why?
    private Set<IngredientCommand> ingredients = new HashSet<>();

    // This is not included by SFG - why? Like other properties, this should be included.
    // private Byte[] image;

    // Using NotesCommand instead of Notes - why?
    private NotesCommand notes;

    private Difficulty difficulty;

    // Using CategoryCommand instead of Category - why?
    private Set<CategoryCommand> categories = new HashSet<>();
}