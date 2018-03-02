package masters.vlad.humeniuk.notesviper.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    private long id;
    private String name;
    private String color;

    public Category() {
    }

    protected Category(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.color = in.readString();
    }

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Category && this.id == ((Category) obj).getId();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.color);
    }
}
