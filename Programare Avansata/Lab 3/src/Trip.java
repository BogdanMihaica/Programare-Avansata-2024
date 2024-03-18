import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Trip{
    private Attraction[] attractions=new Attraction[100];
    private String name;
    public void displayAttractions()
    {
        Arrays.sort(attractions);
        System.out.println("Visitable but not payable attractions: ");
        System.out.println();
        for(Attraction A : attractions)
        {
            if(A.isVisitable() && !A.isPayable())
            {
                System.out.println(A);
            }
        }
    }
    private String timePeriod;
    public Attraction[] getAttractions() {
        return attractions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setAttractions(Attraction ... attractions) {
        this.attractions = attractions;
    }
}
