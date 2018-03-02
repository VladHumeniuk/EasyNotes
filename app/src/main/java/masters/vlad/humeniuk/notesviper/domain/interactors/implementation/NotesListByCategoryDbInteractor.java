package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListByCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

public class NotesListByCategoryDbInteractor implements NotesListByCategoryInteractor {

    private NoteDao noteDao;

    private NoteDbMapper noteDbMapper;

    public NotesListByCategoryDbInteractor(NoteDao noteDao, NoteDbMapper noteDbMapper) {
        this.noteDao = noteDao;
        this.noteDbMapper = noteDbMapper;
    }

    @Override
    public Observable<List<Note>> getNotesListByCategory(Category category) {
        return Observable.fromCallable(() -> noteDao.findByCategory(category.getId()))
                .flatMapIterable(list -> list)
                .map(noteDbMapper::map)
                .map(note -> {
                    note.setCategory(category);
                    return note;
                })
                .toList()
                .toObservable();
    }
}
