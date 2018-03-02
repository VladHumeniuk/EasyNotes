package masters.vlad.humeniuk.notesviper.presentation.main.router;

import android.content.Intent;

public interface MainRouter {

    void showNotesList();

    void showCategories();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
