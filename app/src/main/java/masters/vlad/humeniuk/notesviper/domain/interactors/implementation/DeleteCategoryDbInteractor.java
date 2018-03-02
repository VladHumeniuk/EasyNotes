package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.DeleteCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

public class DeleteCategoryDbInteractor implements DeleteCategoryInteractor {

    private CategoryDao categoryDao;

    private CategoryDbMapper mapper;

    public DeleteCategoryDbInteractor(CategoryDao categoryDao, CategoryDbMapper mapper) {
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public Observable<Boolean> deleteCategory(Category category) {
        return Observable.fromCallable(() -> {
            categoryDao.delete(mapper.mapBack(category));
            return true;
        });
    }
}
