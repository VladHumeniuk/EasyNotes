package masters.vlad.humeniuk.notesviper.presentation.categories.router;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface CategoriesListRouter {

    void showCategoryNotes(Category category);

    void showAddCategory();

    void showEditCategory(Category category);
}
