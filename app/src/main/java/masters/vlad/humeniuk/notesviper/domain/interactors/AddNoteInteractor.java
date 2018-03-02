package masters.vlad.humeniuk.notesviper.domain.interactors;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface AddNoteInteractor {

    Observable<Note> addNote(Note note);
}
