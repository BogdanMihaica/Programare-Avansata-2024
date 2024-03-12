import java.util.List;

public class Trip implements Payable{
    String name;
    int fee;
    int startDay;
    int endDay;
    int startYear;
    int endYear;
    int startMonth;
    int endMonth;
    List<String> attractions;
    @Override
    public String entryFee() {
        return ("The "+ name+ " trip costs "+fee + " dollars.\n");
    }

    @Override
    public String startDate() {
        return ("The "+ name+ " trip starts on "+startDay+"."+startMonth+"."+startYear+"\n");
    }

    @Override
    public String endDate() {
        return ("The "+ name+ " trip ends on "+endDay+"."+endMonth+"."+endYear+"\n");
    }

    @Override
    public String getName() {
        return name;
    }

    public Trip(String name, int fee, String ... attractions) {
        this.name = name;
        this.fee = fee;
        this.attractions = List.of(attractions);
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

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    public String getAttractions() {
        return attractions.toString();
    }

    public void setAttractions(String ... attractions) {
        this.attractions = List.of(attractions);
    }

    @Override
    public String location() {
        return ("The "+ name+ " trip will visit: "+attractions.toString() + "\n");
    }

    @Override
    public String toString() {
        return (
                    "Name of the trip: "+ name+"\n"+
                            entryFee()+
                            startDate()+
                            endDate()+
                            location()
                );
    }
}
