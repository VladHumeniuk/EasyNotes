package masters.vlad.humeniuk.notesviper.domain.interactors;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface DeleteNoteInteractor {

    Observable<Boolean> deleteNote(Note note);
}
