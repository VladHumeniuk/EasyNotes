package masters.vlad.humeniuk.notesviper.di.components;

import dagger.Component;
import masters.vlad.humeniuk.notesviper.di.modules.ActivityModule;
import masters.vlad.humeniuk.notesviper.di.modules.InteractorModule;
import masters.vlad.humeniuk.notesviper.di.modules.MapperModule;
import masters.vlad.humeniuk.notesviper.di.modules.PresenterModule;
import masters.vlad.humeniuk.notesviper.di.modules.RouterModule;
import masters.vlad.humeniuk.notesviper.di.modules.SchedulerModule;
import masters.vlad.humeniuk.notesviper.di.scopes.UserScope;
import masters.vlad.humeniuk.notesviper.presentation.categories.view.CategoriesListFragment;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.CreateCategoryActivity;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.view.CreateCategoryFragment;
import masters.vlad.humeniuk.notesviper.presentation.createnote.CreateNoteActivity;
import masters.vlad.humeniuk.notesviper.presentation.createnote.view.CreateNoteFragment;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.EditCategoryActivity;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.view.EditCategoryFragment;
import masters.vlad.humeniuk.notesviper.presentation.editnote.EditNoteActivity;
import masters.vlad.humeniuk.notesviper.presentation.editnote.view.EditNoteFragment;
import masters.vlad.humeniuk.notesviper.presentation.main.MainActivity;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.view.NotesListFragment;

@UserScope
@Component(dependencies = ApplicationComponent.class,
        modules = {InteractorModule.class,
                ActivityModule.class,
                RouterModule.class,
                PresenterModule.class,
                MapperModule.class,
                SchedulerModule.class
        })
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(CreateNoteActivity activity);

    void inject(EditNoteActivity activity);

    void inject(CreateCategoryActivity activity);

    void inject(EditCategoryActivity activity);

    void inject(NotesListFragment fragment);

    void inject(CreateNoteFragment fragment);

    void inject(EditNoteFragment fragment);

    void inject(CategoriesListFragment fragment);

    void inject(CreateCategoryFragment fragment);

    void inject(EditCategoryFragment fragment);
}
