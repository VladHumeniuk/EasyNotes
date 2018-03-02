package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.EditNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

public class EditNoteDbInteractor implements EditNoteInteractor {

    private NoteDao noteDao;

    private NoteDbMapper mapper;

    public EditNoteDbInteractor(NoteDao noteDao, NoteDbMapper mapper) {
        this.noteDao = noteDao;
        this.mapper = mapper;
    }

    @Override
    public Observable<Note> editNote(Note note) {
        return Observable.fromCallable(() -> {
            noteDao.update(mapper.mapBack(note));
            return note;
        });
    }
}
