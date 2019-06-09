package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final CategoryCommandToCategory categoryConverter;
	private final IngredientCommandToIngredient ingredientConverter;
	private final NoteCommandToNote noteConverter;

	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
			IngredientCommandToIngredient ingredientConverter, NoteCommandToNote noteConverter) {
		super();
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
		this.noteConverter = noteConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}
		final Recipe recipe = new Recipe();

		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setDescription(source.getDescription());
		recipe.setDirection(source.getDirection());
		recipe.setServing(source.getServing());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setUrl(source.getUrl());
		recipe.setSource(source.getSource());
		
		if (source.getNoteCommand() !=null) {
			recipe.setNotes(noteConverter.convert(source.getNoteCommand()));
		}
		

		if (source.getCategoriesCommands() != null && source.getCategoriesCommands().size() > 0) {
			source.getCategoriesCommands().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
		}
		if (source.getIngredientCommands() != null && source.getIngredientCommands().size() > 0) {
			source.getIngredientCommands()
					.forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
		}
		return recipe;
	}
}
