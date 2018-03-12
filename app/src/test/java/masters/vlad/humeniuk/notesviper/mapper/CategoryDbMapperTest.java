package masters.vlad.humeniuk.notesviper.mapper;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;

import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;
import masters.vlad.humeniuk.notesviper.domain.mappers.CategoryDbMapper;

import static org.junit.Assert.assertEquals;

public class CategoryDbMapperTest {

    private CategoryDbMapper categoryDbMapper;

    @Before
    public void init() {
        categoryDbMapper = new CategoryDbMapper();
    }

    @Test
    public void testMapping() {
        DbCategory dbCategory = getDbCategory();
        Category category = categoryDbMapper.map(dbCategory);
        assertCategoriesEquals(category, dbCategory);
    }

    @Test
    public void testReverseMapping() {
        Category category = getCategory();
        DbCategory dbCategory = categoryDbMapper.mapBack(category);
        assertCategoriesEquals(category, dbCategory);
    }

    private Category getCategory() {
        Category category = new Category();
        category.setName("caetgoryName");
        category.setId(1);
        category.setColor("#eeeeee");

        return category;
    }

    private DbCategory getDbCategory() {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setName("caetgoryName");
        dbCategory.setId(1);
        dbCategory.setColor("#eeeeee");

        return dbCategory;
    }

    private void assertCategoriesEquals(Category category, DbCategory dbCategory) {
        assertEquals(category.getId(), dbCategory.getId());
        assertEquals(category.getName(), dbCategory.getName());
        assertEquals(category.getColor(), dbCategory.getColor());
    }
}
