package masters.vlad.humeniuk.notesviper.presentation.editnote.presenter;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.presentation.editnote.view.EditNoteView;

public interface EditNotePresenter {

    void setNote(Note note);

    void saveNote(String title, String description, Category category);

    void setView(EditNoteView view);

    void deleteNote();
}
