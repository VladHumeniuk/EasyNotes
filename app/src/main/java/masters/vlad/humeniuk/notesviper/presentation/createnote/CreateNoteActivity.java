package masters.vlad.humeniuk.notesviper.presentation.createnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.createnote.router.CreateNoteRouter;

public class CreateNoteActivity extends BaseActivity {

    private static final String EXTRA_CATEGORY = "category";

    @Inject
    protected CreateNoteRouter router;

    public static Intent newIntent(Context context) {
        return newIntent(context, null);
    }

    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, CreateNoteActivity.class);
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
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            router.showCreateNoteFragment((Category) getIntent().getExtras().get(EXTRA_CATEGORY));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}
