package masters.vlad.humeniuk.notesviper.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;
import masters.vlad.humeniuk.notesviper.database.entity.DbNote;

@Database(entities = {DbNote.class, DbCategory.class},
        version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public static final String DB_NAME = "notesDatabase.db";

    public abstract NoteDao getNoteDao();

    public abstract CategoryDao getCategoryDao();
}
