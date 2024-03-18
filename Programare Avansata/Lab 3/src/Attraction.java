import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public abstract class Attraction implements Comparable<Attraction>{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attraction(String name) {
        this.name = name;
    }

    private Map<LocalDate, TimeInterval> visitTimeTable;

    public Map<LocalDate, TimeInterval> getVisitTimeTable() {
        return visitTimeTable;
    }

    public void addTime(LocalDate A, TimeInterval B) {
        this.visitTimeTable.put(A,B);
    }
    public abstract boolean isVisitable();
    public abstract boolean isPayable();
    @Override
    public int compareTo(Attraction A) {
        LocalDate today= LocalDate.now();
        if(this.visitTimeTable.get(today).getFirst().isAfter(A.getVisitTimeTable().get(today).getFirst()))
        {
            return 1;
        }
        else return 0;
    }
}
