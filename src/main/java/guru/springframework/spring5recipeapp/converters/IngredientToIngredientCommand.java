package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;

public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
	private RecipeToRecipeCommand recipeCommandConverter;
	private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommandConverter;

	public IngredientToIngredientCommand(RecipeToRecipeCommand recipeCommandConverter,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommandConverter) {
		super();
		this.recipeCommandConverter = recipeCommandConverter;
		this.unitOfMeasureCommandConverter = unitOfMeasureCommandConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		final IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(source.getId());
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setRecipe(recipeCommandConverter.convert(source.getRecipe()));
		ingredientCommand.setUnitOfMeasure(unitOfMeasureCommandConverter.convert(source.getUnitOfMeasure()));
		return ingredientCommand;
	}
}
