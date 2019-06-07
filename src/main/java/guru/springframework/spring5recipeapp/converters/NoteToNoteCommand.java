package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.NoteCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Synchronized;

@Component
public class NoteToNoteCommand implements Converter<Notes, NoteCommand> {

//	private final RecipeToRecipeCommand recipeCommandConventer;
//
//	public NoteToNoteCommand(RecipeToRecipeCommand recipeCommandConventer) {
//		super();
//		this.recipeCommandConventer = recipeCommandConventer;
//	}

	@Synchronized
	@Nullable
	@Override
	public NoteCommand convert(Notes source) {
		if (source == null) {
			return null;
		}
		final NoteCommand noteCommand = new NoteCommand();
		noteCommand.setId(source.getId());
		noteCommand.setRecipeNotes(source.getRecipeNotes());
//		noteCommand.setRecipeCommand(recipeCommandConventer.convert(source.getRecipe()));
		return noteCommand;

	}

}
