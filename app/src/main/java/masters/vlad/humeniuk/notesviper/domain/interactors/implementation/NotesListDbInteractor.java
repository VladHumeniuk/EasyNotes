package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.database.entity.DbNote;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.CategoriesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

public class NotesListDbInteractor implements NotesListInteractor {

    private NoteDao noteDao;

    private NoteDbMapper noteDbMapper;

    private CategoriesListInteractor categoriesListInteractor;

    public NotesListDbInteractor(NoteDao noteDao, NoteDbMapper noteDbMapper,
                                 CategoriesListInteractor categoriesListInteractor) {
        this.noteDao = noteDao;
        this.noteDbMapper = noteDbMapper;
        this.categoriesListInteractor = categoriesListInteractor;
    }

    @Override
    public Observable<List<Note>> getNotesList() {
        return categoriesListInteractor.getCategoriesList()
                .map(this::convertToMap)
                .map(categories -> {
                    List<DbNote> dbNotes = noteDao.findAll();
                    List<Note> notes = new ArrayList<>();
                    for (DbNote dbNote : dbNotes) {
                        Note note = noteDbMapper.map(dbNote);
                        note.setCategory(categories.get(dbNote.getCategoryId()));
                        notes.add(note);
                    }
                    return notes;
                });
    }

    private Map<Long, Category> convertToMap(List<Category> categories) {
        Map<Long, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getId(), category);
        }
        return categoryMap;
    }
}
