package masters.vlad.humeniuk.notesviper.domain.interactors;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public interface DeleteCategoryInteractor {

    Observable<Boolean> deleteCategory(Category category);
}
