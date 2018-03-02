package masters.vlad.humeniuk.notesviper.presentation.main.presenter;

import android.content.Intent;

import masters.vlad.humeniuk.notesviper.presentation.main.view.MainView;

public interface MainPresenter {

    void onNotesListClick();

    void onCategoriesClick();

    void init();

    void onInfoClick();

    void setView(MainView mainView);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
