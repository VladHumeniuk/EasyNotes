package masters.vlad.humeniuk.notesviper.mapper;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import masters.vlad.humeniuk.notesviper.database.entity.DbNote;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

import static org.junit.Assert.assertEquals;

public class NoteDbMapperTest {

    private NoteDbMapper noteDbMapper;

    @Before
    public void init() {
        noteDbMapper = new NoteDbMapper();
    }

    @Test
    public void testMapping() {
        DbNote dbNote = getDbNote();
        Note note = noteDbMapper.map(dbNote);
        assertNotesEqual(note, dbNote);
    }

    @Test
    public void testReverseMapping() {
        Note note = getNote();
        DbNote dbNote = noteDbMapper.mapBack(note);
        assertNotesEqual(note, dbNote);
    }

    private Note getNote() {
        Category category = new Category();
        category.setColor("#eeeeee");
        category.setName("categoryname");
        category.setId(1);

        Note note = new Note();
        note.setId(1);
        note.setDateCreated(new Date(1));
        note.setDateLastEdit(new Date(1));
        note.setTitle("notetitle");
        note.setDescription("notedescription");
        note.setCategory(category);

        return note;
    }

    private DbNote getDbNote() {
        DbNote dbNote = new DbNote();
        dbNote.setCategoryId(1);
        dbNote.setId(1);
        dbNote.setDateCreated(1);
        dbNote.setDateLastEdit(1);
        dbNote.setTitle("notetitle");
        dbNote.setDescription("notedescription");

        return dbNote;
    }

    private void assertNotesEqual(Note note, DbNote dbNote) {
        assertEquals(note.getId(), dbNote.getId());
        assertEquals(note.getCategory().getId(), dbNote.getId());
        assertEquals(note.getDateCreated().getTime(), dbNote.getDateCreated());
        assertEquals(note.getDateLastEdit().getTime(), dbNote.getDateLastEdit());
        assertEquals(note.getDescription(), dbNote.getDescription());
        assertEquals(note.getTitle(), dbNote.getTitle());
    }
}
