package guru.springframework.spring5recipeapp.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			log.error("recipe Id not found. id:" + recipeId);
		}
		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			// TODO implement error handling
			log.error("ingredient Id not found. id:" + recipeId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		if (!recipeOptional.isPresent()) {
			log.error("recipe Id not found. id:" + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();
			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());

				ingredientFound
						.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUnitOfMeasureCommand().getId())
								.orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));// TODO address this
			} else {
				// add new ingredient
				Ingredient ingredient = ingredientCommandToIngredient.convert(command);
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredientCommandToIngredient.convert(command));
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst();

			// check by description
			if (!savedIngredientOptional.isPresent()) {
				// not totaly safe... but best guess
				savedIngredientOptional = savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription()
								.equals(command.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId()
								.equals(command.getUnitOfMeasureCommand().getId()))
						.findFirst();

			}
			// TODO check for fail

			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}

	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();

			log.debug("Found Recipe");
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(idToDelete)).findFirst();
			if (ingredientOptional.isPresent()) {
				log.debug("Found Ingredient");
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientOptional.get());
				recipeRepository.save(recipe);

			}
		} else {
			log.debug("Recipe Id not founda Id: " + recipeId);
		}

	}

}
