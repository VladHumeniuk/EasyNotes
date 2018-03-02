package masters.vlad.humeniuk.notesviper.presentation.noteslist.presenter;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.view.NotesListView;

public interface NotesListPresenter {

    void setCategory(Category category);

    void loadNotes();

    void onNoteSelected(Note note);

    void onCreateNote();

    void setView(NotesListView view);
}
