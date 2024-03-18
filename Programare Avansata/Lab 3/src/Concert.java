
import java.time.LocalTime;
import java.util.Map;


public class Concert extends Attraction implements Payable{
    String type;
    public static boolean visitable = false;
    String name;
    int fee;

    public String getType() {
        return type;
    }

    int startDay;
    int endDay;
   int startMonth;

    int endMonth;
    String location;
    @Override
    public String entryFee() {
        return ("The "+ name+ " concert costs "+fee + " dollars.\n");
    }

    @Override
    public String startDate() {
        return ("The "+ name+ " concert starts on "+startDay+"."+startMonth+".2024\n");
    }

    @Override
    public String endDate() {
        return ("The "+ name+ " concert ends on "+endDay+"."+endMonth+".2024\n");
    }

    @Override
    public String getName() {
        return name;
    }

    public Concert(String name, int fee, String location) {
        super(name);
        this.name = name;
        this.fee = fee;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
        assert getStartDay()<=31 && getStartDay()>=1;
    }

    public int getEndDay() {
        return endDay;

    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
        assert getEndDay()<=31 && getEndDay()>=1;
    }



    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
        assert getStartMonth() >=1 && getStartMonth()<=12;

    }

    public int getEndMonth() {
        return endMonth;

    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
        assert getEndMonth() >=1 && getEndMonth()<=12;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String location() {
        return ("The "+ name+ " concert takes place at "+location+"\n");
    }

    @Override
    public String toString() {
        return (
                "Name of the concert: "+ name+"\n"+
                        entryFee()+
                        startDate()+
                        endDate()+
                        location()+
                "Type of music: " + getType()
        );
    }

    @Override
    public boolean isVisitable() {
        return false;
    }

    @Override
    public boolean isPayable() {
        return true;
    }
}
