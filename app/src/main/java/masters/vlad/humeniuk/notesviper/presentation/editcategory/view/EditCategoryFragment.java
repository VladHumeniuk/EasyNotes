package masters.vlad.humeniuk.notesviper.presentation.editcategory.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseFragment;
import masters.vlad.humeniuk.notesviper.presentation.editcategory.presenter.EditCategoryPresenter;

public class EditCategoryFragment extends BaseFragment implements EditCategoryView {

    private static final String ARG_CATEGORY = "category";

    @BindView(R.id.title_view)
    protected AppCompatEditText titleEditText;

    @BindView(R.id.color_container)
    protected View colorContainer;

    @BindView(R.id.color_view)
    protected AppCompatTextView colorTextView;

    @Inject
    protected EditCategoryPresenter presenter;

    private ColorPicker colorPicker;

    public static EditCategoryFragment newInstance(Category category) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CATEGORY, category);

        EditCategoryFragment fragment = new EditCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        presenter.setCategory(getArguments().getParcelable(ARG_CATEGORY));
        return view;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        presenter.setView(this);
    }

    @Override
    public void showCategory(Category category) {
        titleEditText.setText(category.getName());
        int categoryColor = Color.parseColor(category.getColor());
        colorTextView.setText(getString(R.string.color_format, Integer.toHexString(categoryColor)));
        colorContainer.setBackgroundColor(categoryColor);
        colorPicker = new ColorPicker(getBaseActivity(), Color.red(categoryColor),
                Color.green(categoryColor), Color.blue(categoryColor));
        colorPicker.setCancelable(true);
        colorPicker.setCallback(color -> {
            colorTextView.setText(getString(R.string.color_format, Integer.toHexString(color)));
            colorContainer.setBackgroundColor(color);
            colorPicker.dismiss();
        });
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
    protected int getLayoutId() {
        return R.layout.fragment_create_category;
    }

    @Override
    protected boolean showOptionsMenu() {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save_delete, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save: {
                presenter.onSave(String.valueOf(titleEditText.getText()),
                        String.valueOf(colorTextView.getText()));
                return true;
            }
            case R.id.action_delete: {
                presenter.onDelete();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @OnClick(R.id.color_container)
    protected void onSelectColorClick() {
        colorPicker.show();
    }
}
