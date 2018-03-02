package masters.vlad.humeniuk.notesviper.presentation.createnote.router;

import android.app.Activity;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.view.CreateNoteFragment;

public class CreateNoteRouterImpl extends BaseRouter implements CreateNoteRouter {

    public CreateNoteRouterImpl(BaseActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    public void backToMain() {
        getActivityContext().setResult(Activity.RESULT_OK);
        getActivityContext().finish();
    }

    @Override
    public void showCreateNoteFragment(Category category) {
        showFragment(CreateNoteFragment.newInstance(category), true);
    }
}
