package masters.vlad.humeniuk.notesviper.presentation.editcategory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.router.EditCategoryRouter;

public class EditCategoryActivity extends BaseActivity {

    private static final String EXTRA_CATEGORY = "category";

    @Inject
    protected EditCategoryRouter router;

    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, EditCategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        return intent;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router.showEditCategoryFragment((Category) getIntent().getExtras().get(EXTRA_CATEGORY));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}
