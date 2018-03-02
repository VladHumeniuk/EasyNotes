package masters.vlad.humeniuk.notesviper.presentation.editcategory.presenter;

import android.graphics.Color;
import android.text.TextUtils;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.DeleteCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.EditCategoryInteractor;
import masters.vlad.humeniuk.notesviper.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.router.EditCategoryRouter;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.view.EditCategoryView;

public class EditCategoryPresenterImpl extends RxPresenter implements EditCategoryPresenter {

    private EditCategoryView view;

    private Category category;

    private EditCategoryRouter router;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private EditCategoryInteractor editCategoryInteractor;

    private DeleteCategoryInteractor deleteCategoryInteractor;

    public EditCategoryPresenterImpl(EditCategoryRouter router,
                                     Scheduler ioScheduler,
                                     Scheduler uiScheduler,
                                     EditCategoryInteractor editCategoryInteractor,
                                     DeleteCategoryInteractor deleteCategoryInteractor) {
        this.router = router;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.editCategoryInteractor = editCategoryInteractor;
        this.deleteCategoryInteractor = deleteCategoryInteractor;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
        view.showCategory(category);
    }

    @Override
    public void onSave(String title, String color) {
        if (TextUtils.isEmpty(title) || !isColorValid(color)) {
            view.showEmptyFieldsError();
        } else {
            category.setName(title);
            category.setColor(color);
            saveCategory(category);
        }
    }

    @Override
    public void onDelete() {
        subscribe(1, deleteCategoryInteractor.deleteCategory(category)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> router.backToMain()));
    }

    @Override
    public void setView(EditCategoryView view) {
        this.view = view;
    }

    private void saveCategory(Category category) {
        subscribe(0, editCategoryInteractor.editCategory(category)
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
