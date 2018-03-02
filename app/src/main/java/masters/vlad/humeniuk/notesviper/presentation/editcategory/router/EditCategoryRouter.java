package masters.vlad.humeniuk.notesviper.presentation.editcategory.router;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface EditCategoryRouter {

    void showEditCategoryFragment(Category category);

    void backToMain();
}
