package masters.vlad.humeniuk.notesviper.presentation.editcategory.router;

import android.app.Activity;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.view.EditCategoryFragment;

public class EditCategoryRouterImpl extends BaseRouter implements EditCategoryRouter {

    public EditCategoryRouterImpl(BaseActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    public void showEditCategoryFragment(Category category) {
        showFragment(EditCategoryFragment.newInstance(category), true);
    }

    @Override
    public void backToMain() {
        getActivityContext().setResult(Activity.RESULT_OK);
        getActivityContext().finish();
    }
}
