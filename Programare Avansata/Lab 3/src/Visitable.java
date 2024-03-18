import java.time.LocalDate;
import java.time.LocalTime;

public interface Visitable {
    public default LocalTime getOpeningHour(LocalDate A, Attraction B)
    {
        return B.getVisitTimeTable().get(A).getFirst();
    }
    public abstract String maxTourists();
    public abstract String canBeVisited();
    public abstract String averageNumberOfTourists();
    public abstract String location();
    public abstract String getName();


}
