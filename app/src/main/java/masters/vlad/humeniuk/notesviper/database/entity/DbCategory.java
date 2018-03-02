package masters.vlad.humeniuk.notesviper.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DbCategory {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public interface Columns {

        String ID = "id";

        String NAME = "name";

        String COLOR = "color";
    }
}
