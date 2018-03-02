package masters.vlad.humeniuk.notesviper.presentation.createnote.view.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(@NonNull Context context, List<Category> categoryList) {
        super(context, android.R.layout.simple_spinner_dropdown_item, categoryList);
    }
}
