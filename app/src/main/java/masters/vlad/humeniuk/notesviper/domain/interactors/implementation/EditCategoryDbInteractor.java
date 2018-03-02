package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.EditCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

public class EditCategoryDbInteractor implements EditCategoryInteractor {

    private CategoryDao categoryDao;

    private CategoryDbMapper mapper;

    public EditCategoryDbInteractor(CategoryDao categoryDao, CategoryDbMapper mapper) {
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public Observable<Category> editCategory(Category category) {
        return Observable.fromCallable(() -> {
            categoryDao.update(mapper.mapBack(category));
            return category;
        });
    }
}
