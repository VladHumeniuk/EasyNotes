package masters.vlad.humeniuk.notesviper.presentation.main.router;

import android.content.Intent;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesviper.presentation.categories.view.CategoriesListFragment;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.view.NotesListFragment;

public class MainRouterImpl extends BaseRouter implements MainRouter {

    public MainRouterImpl(BaseActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    public void showNotesList() {
        clearBackStack();
        showFragment(NotesListFragment.newInstance(), true);
    }

    @Override
    public void showCategories() {
        clearBackStack();
        showFragment(CategoriesListFragment.newInstance(), true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getFragmentManager().findFragmentById(getContainerId())
                .onActivityResult(requestCode, resultCode, data);
    }
}
