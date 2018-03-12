package masters.vlad.humeniuk.notesviper.presentation.editnote.view;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseFragment;
import masters.vlad.humeniuk.notesviper.presentation.createnote.view.spinner.CategoryAdapter;
import masters.vlad.humeniuk.notesviper.presentation.editnote.presenter.EditNotePresenter;

public class EditNoteFragment extends BaseFragment implements EditNoteView {

    private static final String NOTE_EXTRA = "note";

    @BindView(R.id.title)
    protected AppCompatEditText titleEditText;

    @BindView(R.id.description)
    protected AppCompatEditText descriptionEditText;

    @BindView(R.id.date_created)
    protected AppCompatTextView createdTextView;

    @BindView(R.id.date_edited)
    protected AppCompatTextView editedTextView;

    @BindView(R.id.category_view)
    protected AppCompatSpinner categorySpinner;

    @Inject
    protected EditNotePresenter presenter;

    private CategoryAdapter adapter;

    private DateFormat dateFormat = new SimpleDateFormat();

    public static EditNoteFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(NOTE_EXTRA, note);

        EditNoteFragment fragment = new EditNoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showNote(Note note) {
        titleEditText.setText(note.getTitle());
        descriptionEditText.setText(note.getDescription());
        createdTextView.setText(getString(R.string.date_created_title,
                dateFormat.format(note.getDateCreated())));
        editedTextView.setText(getString(R.string.date_edited_title,
                dateFormat.format(note.getDateLastEdit())));
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
    public void showCategories(List<Category> categoryList, Category selectedCategory) {
        adapter = new CategoryAdapter(getContext(), categoryList);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(adapter.getPosition(selectedCategory));
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_note;
    }

    @Override
    protected int getTitle() {
        return R.string.note_details_label;
    }

    @Override
    protected void initViews() {
        super.initViews();
        presenter.setNote((Note) getArguments().get(NOTE_EXTRA));
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
                presenter.saveNote(String.valueOf(titleEditText.getText()),
                        String.valueOf(descriptionEditText.getText()),
                        (Category) categorySpinner.getSelectedItem());
                return true;
            }
            case R.id.action_delete: {
                showDeleteConfirmationDialog();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .setTitle(R.string.delete_note_confirmation_text)
                .setPositiveButton(R.string.delete_label, (d, i) -> presenter.deleteNote())
                .setNegativeButton(R.string.cancel_label, (d, i) -> {
                })
                .show();
    }
}
