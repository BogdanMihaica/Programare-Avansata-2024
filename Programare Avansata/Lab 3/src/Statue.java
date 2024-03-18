import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Statue extends Attraction implements Visitable{
    int maxTourists;
    int avgTourists;

    public Statue(String name, String location) {
        super(name);
        this.name = name;
        this.location = location;
    }

    public int getMaxTourists() {
        return maxTourists;
    }

    public void setMaxTourists(int maxTourists) {
        this.maxTourists = maxTourists;
    }

    public int getAvgTourists() {
        return avgTourists;
    }

    public void setAvgTourists(int avgTourists) {
        this.avgTourists = avgTourists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getVisitDays() {
        return visitDays;
    }

    public void setVisitDays(Integer... visitDays) {
        this.visitDays = visitDays;
    }

    public LocalTime[] getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime... startHour) {
        this.startHour = startHour;
    }

    public LocalTime[] getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime ... endHour) {
        this.endHour = endHour;
    }

    String name;
    String location;

    Integer[] visitDays;
    LocalTime[] startHour;
    LocalTime[] endHour;
    public String maxTourists() {
        return ("The "+name+" statue can have a maximum of "+maxTourists+" tourists.\n");
    }

    @Override
    public String canBeVisited() {
        int index=0;
        StringBuilder toReturn=new StringBuilder();
        for(Integer i : visitDays)
        {
            String line= (DayOfWeek.of(i).toString() + " : "+startHour[index++].toString() + " - " + endHour[index-1].toString()+ "\n");
            toReturn.append(line);
        }
        return toReturn.toString();
    }

    @Override
    public String averageNumberOfTourists() {
        return  ("The "+name+" statue has an average of "+avgTourists+" tourists.\n");
    }

    @Override
    public String location() {
        return (location + "\n");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (
                "Statue Name: "+ getName()+"\n"+
                        maxTourists()+averageNumberOfTourists()+location()+canBeVisited()

                );
    }

    @Override
    public boolean isVisitable() {
        return true;
    }

    @Override
    public boolean isPayable() {
        return false;
    }
}
