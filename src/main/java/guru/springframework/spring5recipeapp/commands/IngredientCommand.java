package guru.springframework.spring5recipeapp.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class IngredientCommand {
	private Long id;
	private Long recipeId;
	private UnitOfMeasureCommand unitOfMeasureCommand;
	private String description;
	private BigDecimal amount;

}
