package masters.vlad.humeniuk.notesviper.presentation.editnote.view;

import java.util.List;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;

public interface EditNoteView {

    void showNote(Note note);

    void showEmptyFieldsError();

    void showCategories(List<Category> categoryList, Category selectedCategory);
}
