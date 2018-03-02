package masters.vlad.humeniuk.notesviper.presentation.base.list;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseViewHolder<E>> {

    private LayoutInflater inflater;

    private List<E> entities;

    public BaseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        entities = new ArrayList<>();
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(getItemLayout(viewType), parent, false);

        return createViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(entities.get(position));
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public void setData(List<E> data) {
        entities = data;
        notifyDataSetChanged();
    }

    @LayoutRes
    protected abstract int getItemLayout(int viewType);

    protected abstract BaseViewHolder createViewHolder(View itemView, int viewType);
}
