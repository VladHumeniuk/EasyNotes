package masters.vlad.humeniuk.notesviper.domain.mappers;

import java.util.Date;

import masters.vlad.humeniuk.notesviper.database.entity.DbNote;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public class NoteDbMapper implements BaseMapper<DbNote, Note> {

    @Override
    public Note map(DbNote instance) {
        Note note = new Note();
        note.setId(instance.getId());
        note.setTitle(instance.getTitle());
        note.setDescription(instance.getDescription());
        note.setDateCreated(new Date(instance.getDateCreated()));
        note.setDateLastEdit(new Date(instance.getDateLastEdit()));

        Category category = new Category();
        category.setId(instance.getCategoryId());

        note.setCategory(category);
        return note;
    }

    @Override
    public DbNote mapBack(Note instance) {
        DbNote dbNote = new DbNote();
        dbNote.setId(instance.getId());
        dbNote.setTitle(instance.getTitle());
        dbNote.setDescription(instance.getDescription());
        dbNote.setDateCreated(instance.getDateCreated().getTime());
        dbNote.setDateLastEdit(instance.getDateLastEdit().getTime());
        dbNote.setCategoryId(instance.getCategory() == null ? 1 : instance.getCategory().getId());
        return dbNote;
    }
}
