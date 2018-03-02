package masters.vlad.humeniuk.notesviper.presentation.createcategory.presenter;

import masters.vlad.humeniuk.notesviper.presentation.createcategory.view.CreateCategoryView;

public interface CreateCategoryPresenter {

    void setView(CreateCategoryView view);

    void saveCategory(String title, String color);
}
