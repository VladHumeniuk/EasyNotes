package masters.vlad.humeniuk.notesviper.presentation.noteslist.presenter;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListByCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListInteractor;
import masters.vlad.humeniuk.notesviper.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.router.NotesListRouter;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.view.NotesListView;

public class NotesListPresenterImpl extends RxPresenter implements NotesListPresenter {

    private NotesListRouter router;

    private NotesListView view;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private NotesListInteractor notesListInteractor;

    private NotesListByCategoryInteractor notesListByCategoryInteractor;

    private Category category;

    public NotesListPresenterImpl(NotesListRouter router, Scheduler ioScheduler,
                                  Scheduler uiScheduler, NotesListInteractor notesListInteractor,
                                  NotesListByCategoryInteractor notesListByCategoryInteractor) {
        this.router = router;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.notesListInteractor = notesListInteractor;
        this.notesListByCategoryInteractor = notesListByCategoryInteractor;
    }

    public void setView(NotesListView notesListView) {
        this.view = notesListView;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void loadNotes() {
        if (category == null) {
            loadAllNotes();
        } else {
            loadCategoryNotes();
        }
    }

    @Override
    public void onNoteSelected(Note note) {
        router.openNotesDetails(note);
    }

    @Override
    public void onCreateNote() {
        router.openCreateNote(category);
    }

    private void loadAllNotes() {
        subscribe(0, notesListInteractor.getNotesList()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(view::showNotesList));
    }

    private void loadCategoryNotes() {
        subscribe(0, notesListByCategoryInteractor.getNotesListByCategory(category)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(view::showNotesList));
    }
}
