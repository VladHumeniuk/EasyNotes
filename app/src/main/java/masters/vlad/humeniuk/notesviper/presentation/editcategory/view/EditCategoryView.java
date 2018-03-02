package masters.vlad.humeniuk.notesviper.presentation.editcategory.view;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface EditCategoryView {

    void showCategory(Category category);

    void showEmptyFieldsError();
}
