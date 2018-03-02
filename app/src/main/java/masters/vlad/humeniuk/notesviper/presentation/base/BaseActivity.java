package masters.vlad.humeniuk.notesviper.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.di.components.DaggerActivityComponent;
import masters.vlad.humeniuk.notesviper.di.modules.ActivityModule;
import masters.vlad.humeniuk.notesviper.di.modules.InteractorModule;
import masters.vlad.humeniuk.notesviper.di.modules.MapperModule;
import masters.vlad.humeniuk.notesviper.di.modules.PresenterModule;
import masters.vlad.humeniuk.notesviper.di.modules.RouterModule;
import masters.vlad.humeniuk.notesviper.di.modules.SchedulerModule;
import masters.vlad.humeniuk.notesviper.presentation.NotesApplication;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(NotesApplication.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .interactorModule(new InteractorModule())
                .mapperModule(new MapperModule())
                .presenterModule(new PresenterModule())
                .routerModule(new RouterModule())
                .schedulerModule(new SchedulerModule())
                .build();
        inject(activityComponent);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initViews();
    }

    protected void inject(ActivityComponent activityComponent) {
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            this.finish();
        } else {
            super.onBackPressed();
        }
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected abstract int getLayoutId();

    protected void initViews() {
    }
}
