package masters.vlad.humeniuk.notesviper.presentation.createcategory.router;

import android.app.Activity;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.view.CreateCategoryFragment;

public class CreateCategoryRouterImpl extends BaseRouter implements CreateCategoryRouter {

    public CreateCategoryRouterImpl(BaseActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    public void showCreateCategoryFragment() {
        showFragment(CreateCategoryFragment.newInstance(), true);
    }

    @Override
    public void backToMain() {
        getActivityContext().setResult(Activity.RESULT_OK);
        getActivityContext().finish();
    }
}
