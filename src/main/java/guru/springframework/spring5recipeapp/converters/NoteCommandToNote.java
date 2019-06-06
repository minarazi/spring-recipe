package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.NoteCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Synchronized;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Notes> {

//	private RecipeCommandToRecipe recipeConventer;
	
	
//	public NoteCommandToNote(RecipeCommandToRecipe recipeConventer) {
//		super();
//		this.recipeConventer = recipeConventer;
//	}


	@Synchronized
	@Nullable
	@Override
	public Notes convert(NoteCommand source) {
		if (source == null) {
			return null;
		}
		final Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setRecipeNotes(source.getRecipeNotes());
		return notes;

	}

}
