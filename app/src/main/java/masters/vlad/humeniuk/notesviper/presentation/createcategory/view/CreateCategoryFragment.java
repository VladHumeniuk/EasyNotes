package masters.vlad.humeniuk.notesviper.presentation.createcategory.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseFragment;
import masters.vlad.humeniuk.notesviper.presentation.createcategory.presenter.CreateCategoryPresenter;

public class CreateCategoryFragment extends BaseFragment implements CreateCategoryView {

    @BindView(R.id.title_view)
    protected AppCompatEditText titleEditText;

    @BindView(R.id.color_container)
    protected View colorContainer;

    @BindView(R.id.color_view)
    protected AppCompatTextView colorTextView;

    @Inject
    protected CreateCategoryPresenter presenter;

    private ColorPicker colorPicker;

    public static CreateCategoryFragment newInstance() {

        Bundle args = new Bundle();

        CreateCategoryFragment fragment = new CreateCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showEmptyFieldsError() {
        new AlertDialog.Builder(getBaseActivity())
                .setTitle(R.string.empty_category_fields_error)
                .setCancelable(true)
                .setPositiveButton(R.string.ok_label, null)
                .show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colorPicker = new ColorPicker(getBaseActivity(), 255, 255, 255);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_category;
    }

    @Override
    protected int getTitle() {
        return R.string.create_category_title;
    }

    @Override
    protected boolean showOptionsMenu() {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save: {
                presenter.saveCategory(String.valueOf(titleEditText.getText()),
                        String.valueOf(colorTextView.getText()));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        colorPicker.setCancelable(true);
        colorPicker.setCallback(color -> {
            colorTextView.setText(getString(R.string.color_format, Integer.toHexString(color)));
            colorContainer.setBackgroundColor(color);
            colorPicker.dismiss();
        });
    }

    @OnClick(R.id.color_container)
    protected void onSelectColorClick() {
        colorPicker.show();
    }
}
