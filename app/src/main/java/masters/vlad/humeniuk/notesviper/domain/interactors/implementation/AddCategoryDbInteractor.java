package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

public class AddCategoryDbInteractor implements AddCategoryInteractor {

    private CategoryDao categoryDao;

    private CategoryDbMapper categoryDbMapper;

    public AddCategoryDbInteractor(CategoryDao categoryDao, CategoryDbMapper categoryDbMapper) {
        this.categoryDao = categoryDao;
        this.categoryDbMapper = categoryDbMapper;
    }

    @Override
    public Observable<Category> addCategory(Category category) {
        return Observable.fromCallable(() ->
                categoryDao.insert(categoryDbMapper.mapBack(category)))
                .map(id -> {
                    category.setId(id);
                    return category;
                });
    }
}