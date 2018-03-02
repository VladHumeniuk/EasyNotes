package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.DeleteNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

public class DeleteNoteDbInteractor implements DeleteNoteInteractor {

    private NoteDao noteDao;

    private NoteDbMapper mapper;

    public DeleteNoteDbInteractor(NoteDao noteDao, NoteDbMapper mapper) {
        this.noteDao = noteDao;
        this.mapper = mapper;
    }

    @Override
    public Observable<Boolean> deleteNote(Note note) {
        return Observable.fromCallable(() -> {
            noteDao.delete(mapper.mapBack(note));
            return true;
        });
    }
}
