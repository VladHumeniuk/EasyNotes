package masters.vlad.humeniuk.notesviper.di.modules;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

@Module
public class MapperModule {

    @Provides
    CategoryDbMapper provideCategoryDbMapper() {
        return new CategoryDbMapper();
    }

    @Provides
    NoteDbMapper provideNoteDbMapper() {
        return new NoteDbMapper();
    }
}
