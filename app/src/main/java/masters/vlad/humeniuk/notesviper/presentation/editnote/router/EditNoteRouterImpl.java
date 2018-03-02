package masters.vlad.humeniuk.notesviper.presentation.editnote.router;

import android.app.Activity;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesviper.presentation.editnote.view.EditNoteFragment;

public class EditNoteRouterImpl extends BaseRouter implements EditNoteRouter {

    public EditNoteRouterImpl(BaseActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    public void showEditNoteFragment(Note note) {
        showFragment(EditNoteFragment.newInstance(note), true);
    }

    @Override
    public void backToMain() {
        getActivityContext().setResult(Activity.RESULT_OK);
        getActivityContext().finish();
    }
}
