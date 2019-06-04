package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5recipeapp.services.RecipeService;

@Controller
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable Long id, Model model) {
		model.addAttribute("recipe", recipeService.findById(id));
		return "recipe/show";

	}

}
