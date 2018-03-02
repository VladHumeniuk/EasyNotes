package masters.vlad.humeniuk.notesviper.domain.interactors;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface AddCategoryInteractor {

    Observable<Category> addCategory(Category category);
}
