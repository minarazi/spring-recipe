package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommandConverter;

	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommandConverter) {
		super();
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
		ingredientCommand.setRecipeId(source.getRecipe().getId());
		ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommandConverter.convert(source.getUnitOfMeasure()));
		return ingredientCommand;
	}
}
