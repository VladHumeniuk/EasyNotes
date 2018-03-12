package masters.vlad.humeniuk.notesviper.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    private long id;
    private String title;
    private String description;
    private Date dateCreated;
    private Date dateLastEdit;
    private Category category;

    public Note() {
    }

    protected Note(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
        long tmpDateCreated = in.readLong();
        this.dateCreated = tmpDateCreated == -1 ? null : new Date(tmpDateCreated);
        long tmpDateLastEdit = in.readLong();
        this.dateLastEdit = tmpDateLastEdit == -1 ? null : new Date(tmpDateLastEdit);
        this.category = in.readParcelable(Category.class.getClassLoader());
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastEdit() {
        return dateLastEdit;
    }

    public void setDateLastEdit(Date dateLastEdit) {
        this.dateLastEdit = dateLastEdit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Note
                && id == ((Note) obj).getId()
                && title.equals(((Note) obj).getTitle())
                && description.equals(((Note) obj).getDescription())
                && dateCreated.equals(((Note) obj).getDateCreated())
                && dateLastEdit.equals(((Note) obj).getDateLastEdit())
                && category.equals(((Note) obj).getCategory());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeLong(this.dateCreated != null ? this.dateCreated.getTime() : -1);
        dest.writeLong(this.dateLastEdit != null ? this.dateLastEdit.getTime() : -1);
        dest.writeParcelable(this.category, flags);
    }
}
