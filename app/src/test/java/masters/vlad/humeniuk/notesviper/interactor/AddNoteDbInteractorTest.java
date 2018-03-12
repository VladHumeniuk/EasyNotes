package masters.vlad.humeniuk.notesviper.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import io.reactivex.observers.TestObserver;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.AddNoteDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddNoteDbInteractorTest {

    @Mock
    private NoteDao mockedNoteDao;

    private NoteDbMapper noteDbMapper;

    private TestObserver<Note> testObserver;

    private AddNoteInteractor addNoteInteractor;

    @Before
    public void init() {
        noteDbMapper = new NoteDbMapper();
        testObserver = new TestObserver<>();
        addNoteInteractor = new AddNoteDbInteractor(mockedNoteDao, noteDbMapper);
    }

    @Test
    public void testAddNote() {
        Note note = getNote();
        when(mockedNoteDao.insert(noteDbMapper.mapBack(note))).thenReturn(note.getId());

        addNoteInteractor.addNote(note).subscribe(testObserver);
        verify(mockedNoteDao).insert(noteDbMapper.mapBack(note));
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(note);
    }

    private Note getNote() {
        Note note = new Note();
        note.setId(1);
        note.setTitle("notetitle");
        note.setDescription("notedescription");
        note.setDateCreated(new Date(1));
        note.setDateLastEdit(new Date(1));

        Category category = new Category();
        category.setId(1);
        category.setName("catname");
        category.setColor("#111111");

        note.setCategory(category);

        return note;
    }
}
