package masters.vlad.humeniuk.notesviper.presentation.createcategory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.router.CreateCategoryRouter;

public class CreateCategoryActivity extends BaseActivity {

    @Inject
    CreateCategoryRouter router;

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateCategoryActivity.class);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            router.showCreateCategoryFragment();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}
