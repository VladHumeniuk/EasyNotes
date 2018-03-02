package masters.vlad.humeniuk.notesviper.presentation.main.presenter;

import android.content.Intent;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesviper.domain.interactors.InitDbInteractor;
import masters.vlad.humeniuk.notesviper.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesviper.presentation.main.router.MainRouter;
import masters.vlad.humeniuk.notesviper.presentation.main.view.MainView;

public class MainPresenterImpl extends RxPresenter implements MainPresenter {

    private MainView view;

    private MainRouter mainRouter;

    private Scheduler ioScheduler;

    private InitDbInteractor initDbInteractor;

    public MainPresenterImpl(MainRouter mainRouter, Scheduler
            ioScheduler,
                             InitDbInteractor initDbInteractor) {
        this.mainRouter = mainRouter;
        this.ioScheduler = ioScheduler;
        this.initDbInteractor = initDbInteractor;
    }

    @Override
    public void onNotesListClick() {
        mainRouter.showNotesList();
    }

    @Override
    public void onCategoriesClick() {
        mainRouter.showCategories();
    }

    @Override
    public void init() {
        subscribe(0, initDbInteractor.init()
                .subscribeOn(ioScheduler)
                .subscribe());
    }

    @Override
    public void onInfoClick() {
        view.showInfo();
    }

    @Override
    public void setView(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainRouter.onActivityResult(requestCode, resultCode, data);
    }
}
