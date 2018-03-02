package masters.vlad.humeniuk.notesviper.presentation.editnote.router;

import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface EditNoteRouter {

    void showEditNoteFragment(Note note);

    void backToMain();
}
