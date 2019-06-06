package guru.springframework.spring5recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Notes;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeRepository recipeRepository;
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loading Bootstrap data");
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipe = new ArrayList<>(2);// (initialCapacity:2)

		// get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM  Not Found");
		}

		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("TableSpoon");
		if (!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM  Not Found");
		}

		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("TeaSpoon");
		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM  Not Found");
		}

		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM  Not Found");
		}

		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		if (!pintUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM  Not Found");
		}

		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Excepted UOM  Not Found");
		}

		// get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupsUom = cupsUomOptional.get();

		// get Categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Excepted Category  Not Found");
		}

		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Excepted Category  Not Found");
		}
		// get optinals
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();

		// Guacamole
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirection(
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon."
						+ "/n"
						+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
						+ "/n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown."
						+ "/n"
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness."
						+ "/n"
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
						+ "\n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
						+ "/n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve./n"
						+ "/n" + "/n" + "Read more : https://www.simplyrecipes.com/recipes/perfect_guacamole/");

		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes(
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados."
						+ "/n"
						+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). "
						+ "/n"
						+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole."
						+ "/n"
						+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great."
						+ "/n" + "/n" + "Read more : https://www.simplyrecipes.com/recipes/guacamole_deviled_eggs/");

		guacRecipe.setNotes(guacNotes);

		guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaSpoonUom));
		guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(
				new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(
				new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
		guacRecipe.addIngredient(
				new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

		guacRecipe.getCategories().add(mexicanCategory);
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacRecipe.setServing(4);
		guacRecipe.setSource("simple recipe");
		// add to return list
		recipe.add(guacRecipe);

		// Tacoas
		Recipe tocasRecipe = new Recipe();
		tocasRecipe.setDescription("Spicy Grilled Chicken Tacos");
		tocasRecipe.setPrepTime(15);
		tocasRecipe.setCookTime(30);
		tocasRecipe.setDifficulty(Difficulty.MODERATE);

		tocasRecipe.setDirection("1 Prepare a gas or charcoal grill for medium-high, direct heat." + "\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.Set aside to marinate while the grill heats and you prepare the rest of the toppings."
				+ "\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes. \n"
				+ ".\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
				+ "\n" + "\n" + "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

		Notes tocasNotes = new Notes();
		tocasNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\r\n"
				+ "\r\n" + "\n"
				+ " Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house."
				+ "\n" + "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!"
				+ "\n"
				+ "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings."
				+ "\n"
				+ "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!);\r\n");

		tocasRecipe.setNotes(tocasNotes);

		tocasRecipe.addIngredient(new Ingredient("Cumin", new BigDecimal(1), teaSpoonUom));
		tocasRecipe.addIngredient(new Ingredient("Oregano", new BigDecimal(1), teaSpoonUom));
		tocasRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoonUom));
		tocasRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaSpoonUom));
		tocasRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaSpoonUom));
		tocasRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
		tocasRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
		tocasRecipe.addIngredient(
				new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

		tocasRecipe.getCategories().add(mexicanCategory);
		tocasRecipe.getCategories().add(americanCategory);
		
		tocasRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		tocasRecipe.setServing(4);
		tocasRecipe.setSource("simple recipe");

		recipe.add(tocasRecipe);
		return recipe;

	}

}
