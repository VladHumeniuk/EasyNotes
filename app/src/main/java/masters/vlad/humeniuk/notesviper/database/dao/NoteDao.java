package masters.vlad.humeniuk.notesviper.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import masters.vlad.humeniuk.notesviper.database.entity.DbNote;

@Dao
public interface NoteDao {

    @Insert
    long insert(DbNote dbNote);

    @Update
    void update(DbNote... dbNotes);

    @Delete
    void delete(DbNote... dbNotes);

    @Query("SELECT * FROM dbnote ORDER BY dateLastEdit DESC")
    List<DbNote> findAll();


    @Query("SELECT * FROM dbnote WHERE categoryId=:categoryId ORDER BY dateLastEdit DESC")
    List<DbNote> findByCategory(final long categoryId);
}
