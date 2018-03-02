package masters.vlad.humeniuk.notesviper.di.components;

import javax.inject.Singleton;

import dagger.Component;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.di.modules.ApplicationModule;
import masters.vlad.humeniuk.notesviper.di.modules.DbModule;

@Singleton
@Component(modules = {ApplicationModule.class, DbModule.class})
public interface ApplicationComponent {

    NoteDao getNoteDao();

    CategoryDao getCategoryDao();
}
