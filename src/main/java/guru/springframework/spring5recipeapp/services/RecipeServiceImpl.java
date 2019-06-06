package guru.springframework.spring5recipeapp.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in the Service!");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long l) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(l);

		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe not found!");
		}

		return recipeOptional.get();
	}

//	// for each - lambda
//	public Set<Recipe> getRecipes2() {
//		Set<Recipe> recipeSet = new HashSet<>();
//		Iterable<Recipe> findAll = recipeRepository.findAll();
//		findAll.forEach(r -> {
//			recipeSet.add(r);
//		});
//		return recipeSet;
//	}
//
//	// for each
//	public Set<Recipe> getRecipes3() {
//		Set<Recipe> recipeSet = new HashSet<>();
//		Iterable<Recipe> findAll = recipeRepository.findAll();
//		for (Recipe recipe : findAll) {
//			recipeSet.add(recipe);
//		}
//		return recipeSet;
//	}
//	
//	// for each - method reference
//	//public Set<Recipe> getRecipes4() {
//		//Set<Recipe> recipeSet = new HashSet<>();
//		//recipeRepository.findAll().forEach(recipeSet::add);
//		return recipeSet;
//	}
}