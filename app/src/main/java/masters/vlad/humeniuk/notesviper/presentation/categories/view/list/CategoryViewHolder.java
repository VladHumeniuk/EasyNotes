package masters.vlad.humeniuk.notesviper.presentation.categories.view.list;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import masters.vlad.humeniuk.notesviper.R;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.presentation.base.list.BaseViewHolder;
import masters.vlad.humeniuk.notesviper.presentation.base.list.ItemLongClickSelectedListener;

public class CategoryViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.title)
    protected AppCompatTextView titleView;

    @BindView(R.id.container_view)
    protected View containerView;

    @BindView(R.id.clickable_view)
    protected View clickableView;

    private ItemLongClickSelectedListener<Category> listener;

    public CategoryViewHolder(View itemView,
                              ItemLongClickSelectedListener<Category> listener) {
        super(itemView);
        this.listener = listener;
    }

    @Override
    public void bind(Category entity) {
        super.bind(entity);
        titleView.setText(entity.getName());
        containerView.setBackgroundColor(Color.parseColor(entity.getColor()));
        clickableView.setOnClickListener(v -> listener.onItemSelected(entity));
        clickableView.setOnLongClickListener(v -> {
            listener.onItemLongClick(entity);
            return true;
        });
    }
}
