package masters.vlad.humeniuk.notesviper.presentation.base.list;

public interface ItemLongClickSelectedListener<E> extends ItemSelectedListener<E> {

    void onItemLongClick(E entity);
}
