package masters.vlad.humeniuk.notesviper.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesviper.presentation.main.presenter.MainPresenter;
import masters.vlad.humeniuk.notesviper.presentation.main.view.MainView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.bottom_navigation)
    protected BottomNavigationView bottomNavigationView;
    @Inject
    protected MainPresenter presenter;
    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.action_notes: {
                        presenter.onNotesListClick();
                        return true;
                    }
                    case R.id.action_categories: {
                        presenter.onCategoriesClick();
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        presenter.init();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        bottomNavigationView.setSelectedItemId(R.id.action_notes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info: {
                presenter.onInfoClick();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void showInfo() {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(R.string.info_label)
                .setMessage(R.string.info)
                .setPositiveButton(R.string.ok_label, null)
                .show();
    }
}
