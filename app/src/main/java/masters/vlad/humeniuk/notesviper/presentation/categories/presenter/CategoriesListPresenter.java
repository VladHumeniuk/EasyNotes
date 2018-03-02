package masters.vlad.humeniuk.notesviper.presentation.categories.presenter;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.categories.view.CategoriesListView;

public interface CategoriesListPresenter {

    void onCategorySelected(Category category);

    void onCategoryLongClick(Category category);

    void onAddCategoryClick();

    void init();

    void setView(CategoriesListView view);
}
