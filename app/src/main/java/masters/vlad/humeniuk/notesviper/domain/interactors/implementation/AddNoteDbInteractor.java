package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

public class AddNoteDbInteractor implements AddNoteInteractor {

    private NoteDao noteDao;

    private NoteDbMapper noteDbMapper;

    public AddNoteDbInteractor(NoteDao noteDao, NoteDbMapper noteDbMapper) {
        this.noteDao = noteDao;
        this.noteDbMapper = noteDbMapper;
    }

    @Override
    public Observable<Note> addNote(Note note) {
        return Observable.fromCallable(() ->
                noteDao.insert(noteDbMapper.mapBack(note)))
                .map(id -> {
                    note.setId(id);
                    return note;
                });
    }
}
