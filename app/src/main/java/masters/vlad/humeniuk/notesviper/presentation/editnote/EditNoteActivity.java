package masters.vlad.humeniuk.notesviper.presentation.editnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.editnote.router.EditNoteRouter;

public class EditNoteActivity extends BaseActivity {

    private static final String EXTRA_NOTE = "note";

    @Inject
    protected EditNoteRouter router;

    public static Intent newIntent(Context context, Note note) {
        Intent intent = new Intent(context, EditNoteActivity.class);
        intent.putExtra(EXTRA_NOTE, note);
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
            router.showEditNoteFragment((Note) getIntent().getExtras().get(EXTRA_NOTE));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}
