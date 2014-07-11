package nikolay.rm.vo;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "RISK")
public class Risk implements Comparable {

    @DatabaseField(id = true, generatedId = true, columnName = "id_risk")
    private int id;
    @DatabaseField(columnName = "risk_name", canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private String riskEffect;
    @DatabaseField(columnName = "probability")
    private double occurrenceProbability;
    @DatabaseField(columnName = "consequence")
    private int consequence;
//    @DatabaseField(foreign = true)
//    private Strategy strategy;
    @DatabaseField(foreign = true)
    private Plan plan;

    @DatabaseField(foreign=true, foreignAutoCreate = true)
    private Event event;

    public Risk() {
    }

    public Risk(String name, String riskEffect) {
        this.name = name;
        this.riskEffect = riskEffect;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRiskEffect() {
        return riskEffect;
    }

    public double getOccurrenceProbability() {
        return occurrenceProbability;
    }

    public int getConsequence() {
        return consequence;
    }

//    public Strategy getStrategy() {
//        return strategy;
//    }

    public Plan getPlan() {
        return plan;
    }

    public Event getEvent() {
        return event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRiskEffect(String riskEffect) {
        this.riskEffect = riskEffect;
    }

    public void setOccurrenceProbability(double occurrenceProbability) {
        this.occurrenceProbability = occurrenceProbability;
    }

//    public void setStrategy(Strategy strategy) {
//        this.strategy = strategy;
//    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setConsequence(int consequence) {
        this.consequence = consequence;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public int compareTo(Object another) {
        Risk compared = (Risk) another;
        if (this.occurrenceProbability * this.consequence <
                compared.getOccurrenceProbability() * compared.getConsequence()) {
            return 1;
        }
        if (this.occurrenceProbability * this.consequence >
                compared.getOccurrenceProbability() * compared.getConsequence()) {
            return -1;
        }
        return 0;
    }
}

