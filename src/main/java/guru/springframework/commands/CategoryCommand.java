package guru.springframework.commands;

import guru.springframework.models.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Added new class by tdm on 29th Oct 2020.
 * Command objects are POJOs that will be used by POST and GET requests.
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;

    private String description;

    // This is not included by SFG - why?
    // private Set<Recipe> recipes = new HashSet<>();
}
