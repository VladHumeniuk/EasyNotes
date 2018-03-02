package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.CategoriesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

public class CategoriesListDbInteractor implements CategoriesListInteractor {

    private CategoryDao categoryDao;

    private CategoryDbMapper categoryDbMapper;

    public CategoriesListDbInteractor(CategoryDao categoryDao, CategoryDbMapper categoryDbMapper) {
        this.categoryDao = categoryDao;
        this.categoryDbMapper = categoryDbMapper;
    }

    public Observable<List<Category>> getCategoriesList() {
        return Observable.fromCallable(() -> categoryDao.findAll())
                .flatMapIterable(list -> list)
                .map(categoryDbMapper::map)
                .toList()
                .toObservable();
    }
}
