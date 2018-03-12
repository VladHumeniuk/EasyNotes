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
import masters.vlad.humeniuk.notesviper.domain.interactors.InitDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.implementation.InitDbInteractorImpl;
import masters.vlad.humeniuk.notesviper.domain.utils.CategoryUtil;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InitDbInteractorTest {

    @Mock
    private CategoryDao mockedCategoryDao;

    private InitDbInteractor initDbInteractor;

    private TestObserver<Long> testObserver;

    @Before
    public void init() {
        initDbInteractor = new InitDbInteractorImpl(mockedCategoryDao);
        testObserver = new TestObserver<>();
    }

    @Test
    public void testFirstInit() {
        DbCategory dbCategory = CategoryUtil.getDefaultCategory();
        when(mockedCategoryDao.insert(dbCategory)).thenReturn(dbCategory.getId());

        initDbInteractor.init().subscribe(testObserver);
        verify(mockedCategoryDao).findAll();
        verify(mockedCategoryDao).insert(dbCategory);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(dbCategory.getId());
    }

    @Test
    public void testInit() {
        DbCategory dbCategory = CategoryUtil.getDefaultCategory();
        List<DbCategory> categories = new ArrayList<>();
        categories.add(dbCategory);
        when(mockedCategoryDao.findAll()).thenReturn(categories);

        initDbInteractor.init().subscribe(testObserver);
        verify(mockedCategoryDao).findAll();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertResult(0L);
    }
}
