package masters.vlad.humeniuk.notesviper.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesviper.database.NotesDatabase;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;

@Module
public class DbModule {

    private final NotesDatabase notesDatabase;

    public DbModule(Context context) {
        notesDatabase = Room.databaseBuilder(context,
                NotesDatabase.class,
                NotesDatabase.DB_NAME)
                .fallbackToDestructiveMigration()//TODO implement migration
                .build();
    }

    @Provides
    NoteDao provideNoteDao() {
        return notesDatabase.getNoteDao();
    }

    @Provides
    CategoryDao provideCategoryDao() {
        return notesDatabase.getCategoryDao();
    }
}
