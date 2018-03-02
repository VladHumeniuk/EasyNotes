package masters.vlad.humeniuk.notesviper.presentation.createcategory.presenter;

import android.graphics.Color;
import android.text.TextUtils;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddCategoryInteractor;
import masters.vlad.humeniuk.notesviper.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.router.CreateCategoryRouter;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.view.CreateCategoryView;

public class CreateCategoryPresenterImpl extends RxPresenter implements CreateCategoryPresenter {

    private CreateCategoryView view;

    private CreateCategoryRouter router;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private AddCategoryInteractor addCategoryInteractor;

    public CreateCategoryPresenterImpl(CreateCategoryRouter router,
                                       Scheduler ioScheduler,
                                       Scheduler uiScheduler,
                                       AddCategoryInteractor addCategoryInteractor) {
        this.router = router;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.addCategoryInteractor = addCategoryInteractor;
    }

    @Override
    public void setView(CreateCategoryView view) {
        this.view = view;
    }

    @Override
    public void saveCategory(String title, String color) {
        if (TextUtils.isEmpty(title) || !isColorValid(color)) {
            view.showEmptyFieldsError();
        } else {
            Category category = new Category();
            category.setName(title);
            category.setColor(color);
            saveCategory(category);
        }
    }

    private void saveCategory(Category category) {
        subscribe(0, addCategoryInteractor.addCategory(category)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> router.backToMain()));
    }

    private boolean isColorValid(String color) {
        try {
            Color.parseColor(color);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
