package nikolay.rm.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by nikolay on 11.04.14.
 */
@DatabaseTable(tableName = "EVENT")
public class Event {
    @DatabaseField(columnName = "id_event",id = true, generatedId = true)
    private int id;
    @DatabaseField(columnName = "event_name", canBeNull = false)
    private String name;
    @ForeignCollectionField(eager = false)
    private List<Risk> risks;

    @DatabaseField(foreign=true, foreignAutoRefresh=true, foreignAutoCreate = true)
    private RMProject rmProject;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public RMProject getRmProject() {
        return rmProject;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRisks(List<Risk> risks) {
        this.risks = risks;
    }

    public void setRmProject(RMProject rmProject) {
        this.rmProject = rmProject;
    }
}