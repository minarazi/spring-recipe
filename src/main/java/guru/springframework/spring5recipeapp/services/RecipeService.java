package guru.springframework.spring5recipeapp.services;

import java.util.Set;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	public Recipe findById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);

}
