package masters.vlad.humeniuk.notesviper.interactor;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.AddCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddCategoryDbInteractorTest {

    @Mock
    private CategoryDao mockedCategoryDao;

    private CategoryDbMapper categoryDbMapper;

    private TestObserver<Category> testObserver;

    private AddCategoryDbInteractor addCategoryDbInteractor;

    @Before
    public void init() {
        categoryDbMapper = new CategoryDbMapper();
        testObserver = new TestObserver<>();
        addCategoryDbInteractor = new AddCategoryDbInteractor(mockedCategoryDao, categoryDbMapper);
    }

    @Test
    public void testAdd() {
        when(mockedCategoryDao.insert(categoryDbMapper.mapBack(getCategory())))
                .thenReturn(getCategory().getId());

        addCategoryDbInteractor.addCategory(getCategory()).subscribe(testObserver);
        verify(mockedCategoryDao).insert(categoryDbMapper.mapBack(getCategory()));
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(getCategory());
    }

    public Category getCategory() {
        Category category = new Category();
        category.setName("categoryName");
        category.setColor("#111111");
        category.setId(2);
        return category;
    }
}
