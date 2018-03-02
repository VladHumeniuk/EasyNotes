package masters.vlad.humeniuk.notesviper.domain.utils;

import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;

public class CategoryUtil {

    private static final String DEFAULT_CATEGORY_NAME = "No category";
    private static final String DEFAULT_CATEGORY_COLOR = "#aaaaff";

    public static DbCategory getDefaultCategory() {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setName(DEFAULT_CATEGORY_NAME);
        dbCategory.setId(1);
        dbCategory.setColor(DEFAULT_CATEGORY_COLOR);
        return dbCategory;
    }
}
