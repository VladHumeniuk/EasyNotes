package masters.vlad.humeniuk.notesviper.presentation.categories.view;

import java.util.List;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface CategoriesListView {

    void showCategories(List<Category> categories);
}
