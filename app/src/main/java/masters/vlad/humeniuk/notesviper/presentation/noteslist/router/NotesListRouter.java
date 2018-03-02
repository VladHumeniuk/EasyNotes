package masters.vlad.humeniuk.notesviper.presentation.noteslist.router;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface NotesListRouter {

    void openNotesDetails(Note note);

    void openCreateNote();

    void openCreateNote(Category category);
}
