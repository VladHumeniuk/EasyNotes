package masters.vlad.humeniuk.notesviper.di.modules;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.database.dao.NoteDao;
import masters.vlad.humeniuk.notesviper.di.scopes.UserScope;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.CategoriesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.DeleteCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.DeleteNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.EditCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.EditNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.InitDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListByCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.AddCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.AddNoteDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.CategoriesListDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.DeleteCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.DeleteNoteDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.EditCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.EditNoteDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.InitDbInteractorImpl;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.NotesListByCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.NotesListDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesviper.domain.mappers.NoteDbMapper;

@Module
public class InteractorModule {

    @Provides
    @UserScope
    AddCategoryInteractor provideAddCategoryInteractor(CategoryDao categoryDao,
                                                       CategoryDbMapper categoryDbMapper) {
        return new AddCategoryDbInteractor(categoryDao, categoryDbMapper);
    }

    @Provides
    @UserScope
    CategoriesListInteractor provideCategoriesListInteractor(CategoryDao categoryDao,
                                                             CategoryDbMapper categoryDbMapper) {
        return new CategoriesListDbInteractor(categoryDao, categoryDbMapper);
    }

    @Provides
    @UserScope
    NotesListInteractor provideNotesListInteractor(NoteDao noteDao,
                                                   NoteDbMapper noteDbMapper,
                                                   CategoriesListInteractor categoriesListInteractor) {
        return new NotesListDbInteractor(noteDao, noteDbMapper, categoriesListInteractor);
    }

    @Provides
    @UserScope
    NotesListByCategoryInteractor provideNotesListByCategoryInteractor(NoteDao noteDao,
                                                                       NoteDbMapper noteDbMapper) {
        return new NotesListByCategoryDbInteractor(noteDao, noteDbMapper);
    }

    @Provides
    @UserScope
    AddNoteInteractor provideAddNoteInteractor(NoteDao noteDao,
                                               NoteDbMapper noteDbMapper) {
        return new AddNoteDbInteractor(noteDao, noteDbMapper);
    }

    @Provides
    @UserScope
    InitDbInteractor provideInitDbInteractor(CategoryDao categoryDao) {
        return new InitDbInteractorImpl(categoryDao);
    }

    @Provides
    @UserScope
    EditNoteInteractor provideEditNoteInteractor(NoteDao noteDao,
                                                 NoteDbMapper mapper) {
        return new EditNoteDbInteractor(noteDao, mapper);
    }

    @Provides
    @UserScope
    DeleteNoteInteractor provideDeleteNoteInteractor(NoteDao noteDao,
                                                     NoteDbMapper mapper) {
        return new DeleteNoteDbInteractor(noteDao, mapper);
    }

    @Provides
    @UserScope
    EditCategoryInteractor provideEditCategoryInteractor(CategoryDao categoryDao,
                                                         CategoryDbMapper mapper) {
        return new EditCategoryDbInteractor(categoryDao, mapper);
    }

    @Provides
    @UserScope
    DeleteCategoryInteractor provideDeleteCategoryInteractor(CategoryDao categoryDao,
                                                             CategoryDbMapper mapper) {
        return new DeleteCategoryDbInteractor(categoryDao, mapper);
    }
}
