package masters.vlad.humeniuk.notesviper.domain.mappers;

import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;
import masters.vlad.humeniuk.notesviper.domain.entity.Category;

public class CategoryDbMapper implements BaseMapper<DbCategory, Category> {

    @Override
    public Category map(DbCategory instance) {
        Category category = new Category();
        category.setId(instance.getId());
        category.setColor(instance.getColor());
        category.setName(instance.getName());
        return category;
    }

    @Override
    public DbCategory mapBack(Category instance) {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setId(instance.getId());
        dbCategory.setColor(instance.getColor());
        dbCategory.setName(instance.getName());
        return dbCategory;
    }
}
