import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TravelPlan {
    private HashMap<Attraction, LocalDate> visitDays=new HashMap<>();
    String name;

    public TravelPlan(String name) {

        this.name = name;
    }

    public Map<Attraction, LocalDate> getVisitDay() {
        return visitDays;
    }

    public void setVisitDay(Attraction A, LocalDate B) {
        this.visitDays.put(A,B);
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for(Map.Entry<Attraction, LocalDate> entry : visitDays.entrySet() )
        {
            Attraction key = entry.getKey();
            LocalDate value = entry.getValue();
            String A="Atractie: " + key.getName() + ", Zi: " + value.toString() + '\n';
            toReturn.append(A);
        }
        return toReturn.toString();
    }
}
