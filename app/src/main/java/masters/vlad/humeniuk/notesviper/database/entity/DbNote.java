package masters.vlad.humeniuk.notesviper.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = DbCategory.class,
        parentColumns = DbCategory.Columns.ID,
        childColumns = DbNote.Columns.CATEGORY_ID,
        onDelete = CASCADE))
public class DbNote {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String description;
    private long dateCreated;
    private long dateLastEdit;
    private long categoryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDateLastEdit() {
        return dateLastEdit;
    }

    public void setDateLastEdit(long dateLastEdit) {
        this.dateLastEdit = dateLastEdit;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public interface Columns {

        String ID = "id";

        String TITLE = "title";

        String DESCRIPTION = "description";

        String DATE_CREATED = "dateCreated";

        String DATE_LAST_EDIT = "dateLastEdit";

        String CATEGORY_ID = "categoryId";
    }
}
