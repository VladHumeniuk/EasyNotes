package masters.vlad.humeniuk.notesviper.presentation.noteslist.view;

import java.util.List;

import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface NotesListView {

    void showNotesList(List<Note> notes);
}
