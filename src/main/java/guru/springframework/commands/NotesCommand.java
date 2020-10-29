package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Added new class by tdm on 29th Oct 2020.
 * Command objects are POJOs that will be used by POST and GET requests.
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

    private Long id;
    private String recipeNotes;

    // This is not included by SFG - why? Like other properties, this should be included.
    // private Recipe recipe;
}
