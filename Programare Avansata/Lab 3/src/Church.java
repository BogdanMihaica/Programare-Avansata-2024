import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Church implements Visitable{

    int maxTourists;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    int avgTourists;

    public Church(String name, String location) {
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

    public void setVisitDays(Integer ... visitDays) {
        this.visitDays = visitDays;
    }

    public LocalTime[] getStartHour() {
        return startHour;
    }

    public void setStartHours(LocalTime ... startHour) {
        this.startHour = startHour;
    }

    public LocalTime[] getEndHour() {
        return endHour;
    }

    public void setEndHours(LocalTime ... endHour) {
        this.endHour = endHour;
    }

    String name;
    String location;

    Integer[] visitDays;
    LocalTime[] startHour;
    LocalTime[] endHour;
    public String maxTourists() {
        return ("The "+name+" church can have a maximum of "+maxTourists+" tourists.\n");
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
        return  ("The "+name+" church has an average of "+avgTourists+" tourists.\n");
    }

    @Override
    public String location() {
        return (location + "\n");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (
                "church Name: "+ getName()+"\n"+
                        "Type of Church: "+ getType()+ "\n"+
                        maxTourists()+averageNumberOfTourists()+location()+canBeVisited()


        );
    }
}
