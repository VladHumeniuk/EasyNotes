package masters.vlad.humeniuk.notesviper.presentation.createnote.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseFragment;
import masters.vlad.humeniuk.notesviper.presentation.createnote.presenter.CreateNotePresenter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.view.spinner.CategoryAdapter;

public class CreateNoteFragment extends BaseFragment implements CreateNoteView {

    private static final String ARG_CATEGORY = "category";

    @BindView(R.id.title)
    protected AppCompatEditText titleEditText;

    @BindView(R.id.description)
    protected AppCompatEditText descriptionEditText;

    @BindView(R.id.category_view)
    protected AppCompatSpinner categorySpinner;

    @Inject
    protected CreateNotePresenter presenter;

    private CategoryAdapter adapter;

    public static CreateNoteFragment newInstance(Category category) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CATEGORY, category);

        CreateNoteFragment fragment = new CreateNoteFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_note;
    }

    @Override
    protected int getTitle() {
        return R.string.create_note_title;
    }

    @Override
    public void showEmptyFieldsError() {
        new AlertDialog.Builder(getBaseActivity())
                .setTitle(R.string.empty_note_fields_error)
                .setPositiveButton(R.string.ok_label, null)
                .setCancelable(true)
                .show();
    }

    @Override
    public void showDefaultCategory(Category category) {
        categorySpinner.setSelection(adapter.getPosition(category));
    }

    @Override
    public void showCategories(List<Category> categoryList) {
        adapter = new CategoryAdapter(getContext(), categoryList);
        categorySpinner.setAdapter(adapter);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        presenter.setView(this);
        presenter.setDefaultCategory((Category) getArguments().get(ARG_CATEGORY));
        return view;
    }

    @Override
    protected boolean showOptionsMenu() {
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save: {
                presenter.onSaveNote(String.valueOf(titleEditText.getText()),
                        String.valueOf(descriptionEditText.getText()),
                        (Category) categorySpinner.getSelectedItem());
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
