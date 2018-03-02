package masters.vlad.humeniuk.notesviper.domain.interactors;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface NotesListByCategoryInteractor {

    Observable<List<Note>> getNotesListByCategory(Category category);
}
