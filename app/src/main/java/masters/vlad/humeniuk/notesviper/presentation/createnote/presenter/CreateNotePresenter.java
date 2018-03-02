package masters.vlad.humeniuk.notesviper.presentation.createnote.presenter;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.createnote.view.CreateNoteView;

public interface CreateNotePresenter {

    void onSaveNote(String title, String description, Category category);

    void setView(CreateNoteView view);

    void setDefaultCategory(Category category);
}
