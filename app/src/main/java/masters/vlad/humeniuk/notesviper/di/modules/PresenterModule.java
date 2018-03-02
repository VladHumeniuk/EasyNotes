package masters.vlad.humeniuk.notesviper.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
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
import masters.vlad.humeniuk.notesviper.presentation.categories.presenter.CategoriesListPresenter;
import masters.vlad.humeniuk.notesviper.presentation.categories.presenter.CategoriesListPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.categories.router.CategoriesListRouter;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.presenter.CreateCategoryPresenter;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.presenter.CreateCategoryPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.router.CreateCategoryRouter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.presenter.CreateNotePresenter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.presenter.CreateNotePresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.createnote.router.CreateNoteRouter;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.presenter.EditCategoryPresenter;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.presenter.EditCategoryPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.router.EditCategoryRouter;
import masters.vlad.humeniuk.notesviper.presentation.editnote.presenter.EditNotePresenter;
import masters.vlad.humeniuk.notesviper.presentation.editnote.presenter.EditNotePresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.editnote.router.EditNoteRouter;
import masters.vlad.humeniuk.notesviper.presentation.main.presenter.MainPresenter;
import masters.vlad.humeniuk.notesviper.presentation.main.presenter.MainPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.main.router.MainRouter;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.presenter.NotesListPresenter;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.presenter.NotesListPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.router.NotesListRouter;

import static masters.vlad.humeniuk.notesviper.di.modules.SchedulerModule.IO_SCHEDULER;
import static masters.vlad.humeniuk.notesviper.di.modules.SchedulerModule.UI_SCHEDULER;

@Module
public class PresenterModule {

    @Provides
    @UserScope
    MainPresenter provideMainPresenter(MainRouter mainRouter,
                                       @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                       InitDbInteractor initDbInteractor) {
        return new MainPresenterImpl(mainRouter, ioScheduler, initDbInteractor);
    }

    @Provides
    @UserScope
    NotesListPresenter provideNotesListPresenter(NotesListRouter notesListRouter,
                                                 @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                 @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                 NotesListInteractor notesListInteractor,
                                                 NotesListByCategoryInteractor notesListByCategoryInteractor) {
        return new NotesListPresenterImpl(notesListRouter, ioScheduler, uiScheduler,
                notesListInteractor, notesListByCategoryInteractor);
    }

    @Provides
    @UserScope
    CreateNotePresenter provideCreateNotePresenter(CreateNoteRouter router,
                                                   @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                   @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                   AddNoteInteractor addNoteInteractor,
                                                   CategoriesListInteractor categoriesListInteractor) {
        return new CreateNotePresenterImpl(router, ioScheduler, uiScheduler,
                addNoteInteractor, categoriesListInteractor);
    }

    @Provides
    @UserScope
    EditNotePresenter provideEditNotePresenter(EditNoteRouter router,
                                               @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                               @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                               EditNoteInteractor editNoteInteractor,
                                               DeleteNoteInteractor deleteNoteInteractor,
                                               CategoriesListInteractor categoriesListInteractor) {
        return new EditNotePresenterImpl(router, ioScheduler, uiScheduler,
                editNoteInteractor, deleteNoteInteractor, categoriesListInteractor);
    }

    @Provides
    @UserScope
    CategoriesListPresenter provideCategoriesListPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                           @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                           CategoriesListRouter router,
                                                           CategoriesListInteractor categoriesListInteractor) {
        return new CategoriesListPresenterImpl(ioScheduler, uiScheduler,
                router, categoriesListInteractor);
    }

    @Provides
    @UserScope
    CreateCategoryPresenter provideCreateCategoryPresenter(CreateCategoryRouter router,
                                                           @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                           @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                           AddCategoryInteractor interactor) {
        return new CreateCategoryPresenterImpl(router, ioScheduler, uiScheduler, interactor);
    }

    @Provides
    @UserScope
    EditCategoryPresenter provideEditCategoryPresenter(EditCategoryRouter router,
                                                       @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                       @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                       EditCategoryInteractor editCategoryInteractor,
                                                       DeleteCategoryInteractor deleteCategoryInteractor) {
        return new EditCategoryPresenterImpl(router, ioScheduler, uiScheduler,
                editCategoryInteractor, deleteCategoryInteractor);
    }
}
