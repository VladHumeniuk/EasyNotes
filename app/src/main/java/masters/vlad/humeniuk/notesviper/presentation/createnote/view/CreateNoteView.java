package masters.vlad.humeniuk.notesviper.presentation.createnote.view;

import java.util.List;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface CreateNoteView {

    void showEmptyFieldsError();

    void showDefaultCategory(Category category);

    void showCategories(List<Category> categoryList);
}
