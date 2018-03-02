package masters.vlad.humeniuk.notesviper.presentation.base.list;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import butterknife.ButterKnife;

public class BaseViewHolder<E> extends ViewHolder {

    private E entity;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(E entity) {
        this.entity = entity;
    }

    protected E getEntity() {
        return entity;
    }
}
