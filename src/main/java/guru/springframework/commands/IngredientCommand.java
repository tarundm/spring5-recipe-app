package guru.springframework.commands;

import guru.springframework.models.Recipe;
import guru.springframework.models.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Added new class by tdm on 29th Oct 2020.
 * Command objects are POJOs that will be used by POST and GET requests.
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal amount;

    // Using UnitOfMeasureCommand instead of UnitOfMeasure - why?
    private UnitOfMeasureCommand unitOfMeasure;

    // This is not included by SFG - why?
    // private Recipe recipe;
}
