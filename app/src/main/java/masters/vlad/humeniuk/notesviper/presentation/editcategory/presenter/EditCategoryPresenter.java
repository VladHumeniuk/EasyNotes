package masters.vlad.humeniuk.notesviper.presentation.editcategory.presenter;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.view.EditCategoryView;

public interface EditCategoryPresenter {

    void setCategory(Category category);

    void onSave(String title, String color);

    void onDelete();

    void setView(EditCategoryView view);
}
