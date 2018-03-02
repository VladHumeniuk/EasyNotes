package masters.vlad.humeniuk.notesviper.domain.interactors.implementation;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;
import masters.vlad.humeniuk.notesviper.domain.interactors.InitDbInteractor;

public class InitDbInteractorImpl implements InitDbInteractor {

    private CategoryDao categoryDao;

    public InitDbInteractorImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Observable<Long> init() {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setName("No category");
        dbCategory.setId(0);
        dbCategory.setColor("#aaaaff");
        return Observable.fromCallable(() -> categoryDao.findAll())
                .map(List::size)
                .map(size -> size == 0 ? categoryDao.insert(dbCategory) : 0l);
    }
}
