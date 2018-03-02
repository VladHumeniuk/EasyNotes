package masters.vlad.humeniuk.notesviper.presentation.createnote.router;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface CreateNoteRouter {

    void backToMain();

    void showCreateNoteFragment(Category category);
}
