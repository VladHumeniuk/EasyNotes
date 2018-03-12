package masters.vlad.humeniuk.notesviper.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.database.entity.DbNote;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListByCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.NotesListByCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;
import masters.vlad.humeniuk.notesviper.domain.utils.CategoryUtil;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotesByCategoryDbInteractorTest {

    @Mock
    private NoteDao mockedNoteDao;

    private NoteDbMapper noteDbMapper;

    private CategoryDbMapper categoryDbMapper;

    private TestObserver<List<Note>> testObserver;

    private NotesListByCategoryInteractor notesListInteractor;

    @Before
    public void init() {
        noteDbMapper = new NoteDbMapper();
        categoryDbMapper = new CategoryDbMapper();
        testObserver = new TestObserver<>();
        notesListInteractor = new NotesListByCategoryDbInteractor(mockedNoteDao, noteDbMapper);
    }

    @Test
    public void testGetList() {
        when(mockedNoteDao.findByCategory(CategoryUtil.getDefaultCategory().getId())).thenReturn(getDbNotes());

        notesListInteractor.getNotesListByCategory(categoryDbMapper.map(CategoryUtil.getDefaultCategory()))
                .subscribe(testObserver);

        verify(mockedNoteDao).findByCategory(CategoryUtil.getDefaultCategory().getId());
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(getNotes());
    }

    private List<DbNote> getDbNotes() {
        List<DbNote> dbNotes = new ArrayList<>();
        DbNote note1 = new DbNote();
        note1.setId(1);
        note1.setDateCreated(1);
        note1.setDateLastEdit(4);
        note1.setCategoryId(1);
        note1.setDescription("note1d");
        note1.setTitle("note1t");

        DbNote note2 = new DbNote();
        note2.setId(2);
        note2.setDateCreated(2);
        note2.setDateLastEdit(3);
        note2.setCategoryId(1);
        note2.setDescription("note2d");
        note2.setTitle("note2t");

        dbNotes.add(note1);
        dbNotes.add(note2);

        return dbNotes;
    }

    private List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        List<DbNote> dbNotes = getDbNotes();

        Note note1 = noteDbMapper.map(dbNotes.get(0));
        note1.setCategory(categoryDbMapper.map(CategoryUtil.getDefaultCategory()));

        Note note2 = noteDbMapper.map(dbNotes.get(1));
        note2.setCategory(categoryDbMapper.map(CategoryUtil.getDefaultCategory()));

        notes.add(note1);
        notes.add(note2);

        return notes;
    }
}
