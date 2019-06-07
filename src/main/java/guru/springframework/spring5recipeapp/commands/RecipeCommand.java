package guru.springframework.spring5recipeapp.commands;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	private String direction;
	private Difficulty difficulty;
	private Byte[] image;
	private Set<IngredientCommand> ingredientCommands = new HashSet<IngredientCommand>();
	private NoteCommand noteCommand;
	private Set<CategoryCommand> categoriesCommands = new HashSet<CategoryCommand>();
	

}
