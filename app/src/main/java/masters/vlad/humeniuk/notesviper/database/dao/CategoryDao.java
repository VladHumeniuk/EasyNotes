package masters.vlad.humeniuk.notesviper.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import masters.vlad.humeniuk.notesviper.database.entity.DbCategory;

@Dao
public interface CategoryDao {

    @Insert
    long insert(DbCategory dbCategory);

    @Update
    void update(DbCategory... dbCategories);

    @Delete
    void delete(DbCategory... dbCategories);

    @Query("SELECT * FROM dbcategory")
    List<DbCategory> findAll();
}
