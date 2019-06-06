package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
//	private RecipeCommandToRecipe recipeConverter;
//	private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter;
//
//	public IngredientCommandToIngredient(RecipeCommandToRecipe recipeConverter,
//			UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter) {
//		super();
//		this.recipeConverter = recipeConverter;
//		this.unitOfMeasureConverter = unitOfMeasureConverter;
//	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) {
			return null;
		}
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());

		return ingredient;
	}

}
