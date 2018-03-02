package masters.vlad.humeniuk.notesviper.presentation.noteslist.view.list;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.domain.entity.Note;
import masters.vlad.humeniuk.notesviper.presentation.base.list.BaseViewHolder;
import masters.vlad.humeniuk.notesviper.presentation.base.list.ItemSelectedListener;

public class NoteViewHolder extends BaseViewHolder<Note> {

    @BindView(R.id.title)
    protected AppCompatTextView titleView;

    @BindView(R.id.container_view)
    protected View containerView;

    @BindView(R.id.clickable_view)
    protected View clickableView;

    private ItemSelectedListener<Note> listener;

    public NoteViewHolder(View itemView, ItemSelectedListener<Note> listener) {
        super(itemView);
        this.listener = listener;
    }

    @Override
    public void bind(Note entity) {
        super.bind(entity);
        titleView.setText(entity.getTitle());
        containerView.setBackgroundColor(Color.parseColor(entity.getCategory().getColor()));
        clickableView.setOnClickListener(v -> listener.onItemSelected(entity));
    }
}
