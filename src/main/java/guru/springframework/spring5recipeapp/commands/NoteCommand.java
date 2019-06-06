package guru.springframework.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class NoteCommand {

	private Long id;
	private RecipeCommand recipeCommand;
	private String recipeNotes;
}
