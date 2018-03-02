package masters.vlad.humeniuk.notesviper.presentation.createnote.presenter;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.CategoriesListInteractor;
import masters.vlad.humeniuk.notesviper.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.router.CreateNoteRouter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.view.CreateNoteView;

public class CreateNotePresenterImpl extends RxPresenter implements CreateNotePresenter {

    private CreateNoteView view;

    private CreateNoteRouter router;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private AddNoteInteractor addNoteInteractor;

    private CategoriesListInteractor categoriesListInteractor;

    public CreateNotePresenterImpl(CreateNoteRouter router, Scheduler ioScheduler,
                                   Scheduler uiScheduler, AddNoteInteractor addNoteInteractor,
                                   CategoriesListInteractor categoriesListInteractor) {
        this.router = router;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.addNoteInteractor = addNoteInteractor;
        this.categoriesListInteractor = categoriesListInteractor;
    }

    @Override
    public void onSaveNote(String title, String description, Category category) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            view.showEmptyFieldsError();
        } else {
            Note note = new Note();
            note.setTitle(title);
            note.setDescription(description);
            Date date = Calendar.getInstance().getTime();
            note.setDateCreated(date);
            note.setDateLastEdit(date);
            note.setCategory(category);
            saveNote(note);
        }
    }

    @Override
    public void setView(CreateNoteView view) {
        this.view = view;
    }

    @Override
    public void setDefaultCategory(Category category) {
        loadCategories(category);
    }

    private void saveNote(Note note) {
        subscribe(0, addNoteInteractor.addNote(note)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> router.backToMain()));
    }

    private void loadCategories(Category defaultCategory) {
        subscribe(1, categoriesListInteractor.getCategoriesList()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(list -> {
                    view.showCategories(list);
                    if (defaultCategory != null) {
                        view.showDefaultCategory(defaultCategory);
                    }
                }));
    }
}
