import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TravelPlan Plan = new TravelPlan("Plan de vizitare");
        Church SfMaria = new Church("Sfanta Maria Regina", "Tamaseni");
        Church SfParascheva = new Church("Sfanta Parascheva", "Iasi");
        Concert BeachPlease = new Concert("Beach Please", 400, "Mamaia");
        Concert Untold = new Concert("Untold", 200, "Cluj");
        Statue Sfinx = new Statue("Sfinxul", "Muntii Bucegi");
        Statue Hydra = new Statue("Hydra", "Iasi");
        Trip trip= new Trip();
        Plan.setVisitDay(Sfinx, LocalDate.of(2024, 11, 12));
        Plan.setVisitDay(Hydra, LocalDate.of(2024, 9, 11));
        Plan.setVisitDay(SfParascheva, LocalDate.of(2024, 8, 6));
        Plan.setVisitDay(SfMaria, LocalDate.of(2024, 10, 14));
        Plan.setVisitDay(BeachPlease, LocalDate.of(2024, 7, 11));
        Plan.setVisitDay(Untold, LocalDate.of(2024, 8, 8));
        System.out.println(Plan);
        trip.setName("Prin Romania");
        trip.setTimePeriod("11.07.2024 - 12.11.2024");
        trip.setAttractions(Sfinx,SfMaria,BeachPlease,Untold,Hydra,SfParascheva);
    }
}