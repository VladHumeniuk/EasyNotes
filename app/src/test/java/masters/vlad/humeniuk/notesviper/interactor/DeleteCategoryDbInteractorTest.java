package masters.vlad.humeniuk.notesviper.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import masters.vlad.humeniuk.notesviper.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.interactors.DeleteCategoryInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.DeleteCategoryDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCategoryDbInteractorTest {

    @Mock
    private CategoryDao mockedCategoryDao;

    private CategoryDbMapper categoryDbMapper;

    private TestObserver<Boolean> testObserver;

    private DeleteCategoryInteractor deleteCategoryInteractor;

    @Before
    public void init() {
        categoryDbMapper = new CategoryDbMapper();
        testObserver = new TestObserver<>();
        deleteCategoryInteractor = new DeleteCategoryDbInteractor(mockedCategoryDao, categoryDbMapper);
    }

    @Test
    public void testDelete() {

        deleteCategoryInteractor.deleteCategory(getCategory()).subscribe(testObserver);
        verify(mockedCategoryDao).delete(categoryDbMapper.mapBack(getCategory()));
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(true);
    }

    public Category getCategory() {
        Category category = new Category();
        category.setName("categoryName");
        category.setColor("#111111");
        category.setId(2);
        return category;
    }

}
