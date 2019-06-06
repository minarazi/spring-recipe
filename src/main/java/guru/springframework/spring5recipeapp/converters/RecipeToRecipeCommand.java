package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final CategoryToCategoryCommand categoryCommandConverter;
	private final IngredientToIngredientCommand ingredientCommandConverter;
	private final NoteToNoteCommand noteCommandConverter;

	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryCommandConverter,
			IngredientToIngredientCommand ingredientCommandConverter, NoteToNoteCommand noteCommandConverter) {
		super();
		this.categoryCommandConverter = categoryCommandConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.noteCommandConverter = noteCommandConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}
		final RecipeCommand recipeCommand = new RecipeCommand();

		recipeCommand.setId(source.getId());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDirection(source.getDirection());
		recipeCommand.setServing(source.getServing());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setUrl(source.getUrl());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setNotes(noteCommandConverter.convert(source.getNotes()));

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories()
					.forEach(category -> recipeCommand.getCategories().add(categoryCommandConverter.convert(category)));
		}
		if (source.getIngredient() != null && source.getIngredient().size() > 0) {
			source.getIngredient().forEach(
					ingredient -> recipeCommand.getIngredient().add(ingredientCommandConverter.convert(ingredient)));
		}
		return recipeCommand;
	}

}
