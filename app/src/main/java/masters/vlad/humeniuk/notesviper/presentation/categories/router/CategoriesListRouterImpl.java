package masters.vlad.humeniuk.notesviper.presentation.categories.router;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.CreateCategoryActivity;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.EditCategoryActivity;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.view.NotesListFragment;

public class CategoriesListRouterImpl extends BaseRouter implements CategoriesListRouter {

    public static final int REQUEST_ADD_CATEGORY = 13;
    public static final int REQUEST_EDIT_CATEGORY = 14;

    public CategoriesListRouterImpl(BaseActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    public void showCategoryNotes(Category category) {
        showFragment(NotesListFragment.newInstance(category), true);
    }

    @Override
    public void showAddCategory() {
        startActivityForResult(CreateCategoryActivity.newIntent(getActivityContext()),
                REQUEST_ADD_CATEGORY);
    }

    @Override
    public void showEditCategory(Category category) {
        startActivityForResult(EditCategoryActivity.newIntent(getActivityContext(), category),
                REQUEST_EDIT_CATEGORY);
    }
}
