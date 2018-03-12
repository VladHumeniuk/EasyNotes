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
import masters.vlad.humeniuk.notesviper.domain.interactors.EditNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.EditNoteDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EditNoteDbInteractorTest {

    @Mock
    private NoteDao mockedNoteDao;

    private NoteDbMapper noteDbMapper;

    private TestObserver<Note> testObserver;

    private EditNoteInteractor editNoteInteractor;

    @Before
    public void init() {
        noteDbMapper = new NoteDbMapper();
        testObserver = new TestObserver<>();
        editNoteInteractor = new EditNoteDbInteractor(mockedNoteDao, noteDbMapper);
    }

    @Test
    public void testEditNote() {
        Note note = getNote();

        editNoteInteractor.editNote(note).subscribe(testObserver);
        verify(mockedNoteDao).update(noteDbMapper.mapBack(note));
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
