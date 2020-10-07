package guru.springframework.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String uom;
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name="recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);
        return this;
    }

    // Moved code that sets recipe from RecipeBootstrap to here so that when Constraints is not defined
    // we can implicitly set recipe on notes object
    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }
}
