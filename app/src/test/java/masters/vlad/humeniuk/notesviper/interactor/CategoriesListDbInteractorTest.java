package masters.vlad.humeniuk.notesviper.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.CategoriesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.CategoriesListDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesviper.domain.utils.CategoryUtil;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesListDbInteractorTest {

    @Mock
    private CategoryDao mockedCategoryDao;

    private TestObserver<List<Category>> testObserver;

    private CategoriesListInteractor categoriesListInteractor;

    private CategoryDbMapper categoryDbMapper;

    @Before
    public void init() {
        testObserver = new TestObserver<>();
        categoryDbMapper = new CategoryDbMapper();
        categoriesListInteractor = new CategoriesListDbInteractor(mockedCategoryDao,
                categoryDbMapper);
    }

    @Test
    public void testGetList() {
        when(mockedCategoryDao.findAll()).thenReturn(getDbCategories());

        categoriesListInteractor.getCategoriesList().subscribe(testObserver);
        verify(mockedCategoryDao).findAll();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(getCategories());
    }

    private List<DbCategory> getDbCategories() {
        List<DbCategory> dbCategories = new ArrayList<>();
        DbCategory dbCategory = new DbCategory();
        dbCategory.setId(2);
        dbCategory.setColor("#111111");
        dbCategory.setName("categoryName");

        dbCategories.add(CategoryUtil.getDefaultCategory());
        dbCategories.add(dbCategory);

        return dbCategories;
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        for (DbCategory dbCategory : getDbCategories()) {
            categories.add(categoryDbMapper.map(dbCategory));
        }
        return categories;
    }
}
