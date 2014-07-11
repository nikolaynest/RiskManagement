package nikolay.rm.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PLAN")
public class Plan {
    @DatabaseField(id = true, generatedId = true, columnName = "id_plan")
    private int id;
    @DatabaseField(canBeNull = false)
    private String planA;
    @DatabaseField(canBeNull = false)
    private String planB;

    public Plan() {
    }

    public Plan(String planA, String planB) {
        this.planA = planA;
        this.planB = planB;
    }

    public int getId() {
        return id;
    }

    public String getPlanA() {
        return planA;
    }

    public String getPlanB() {
        return planB;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanA(String planA) {
        this.planA = planA;
    }

    public void setPlanB(String planB) {
        this.planB = planB;
    }
}
