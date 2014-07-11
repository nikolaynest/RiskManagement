package nikolay.rm.vo;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by nikolay on 11.04.14.
 */
@DatabaseTable(tableName = "PROJECT")
public class RMProject implements Parcelable{
    @DatabaseField(columnName = "id_project", id = true, generatedId = true)
    private int id;
    @DatabaseField(columnName = "project_name", canBeNull = false)
    private String name;
    @ForeignCollectionField(eager = false)
    private List<Event> events;

    public RMProject() {}
    public RMProject(Parcel in){
        this.name = in.readString();
    }

    public RMProject(String name) {
        this.name = name;
    }

    public void createProject(String name){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "RMProject{" +
                "id=" + id +
                ", name='" + name + "'}\n";

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public RMProject createFromParcel(Parcel in) {
            return new RMProject(in);
        }

        public RMProject[] newArray(int size) {
            return new RMProject[size];
        }
    };
}

