import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Church antonDePadova=new Church("Anton de Padova", "Iasi");
        Concert beachPlease = new Concert("Beach Please", 100, "Constanta");
        Statue hydra = new Statue("Hydra", "Iasi");
        Trip aroundEurope = new Trip("Around Europe", 500, "Tour Eiffel", "Tour of Pisa", "Omu'");

        antonDePadova.setAvgTourists(50);
        antonDePadova.setVisitDays(1,2,3,4,5,6,7);
        antonDePadova.setStartHours(LocalTime.of(8,0),LocalTime.of(8,0),LocalTime.of(8,0),LocalTime.of(8,0),LocalTime.of(8,0),LocalTime.of(13,0),LocalTime.of(13,0));
        antonDePadova.setEndHours(LocalTime.of(20,0),LocalTime.of(20,0),LocalTime.of(20,0),LocalTime.of(20,0),LocalTime.of(20,0),LocalTime.of(18,0),LocalTime.of(18,0));
        antonDePadova.setMaxTourists(100);
        antonDePadova.setType("Catholic");
        System.out.println(antonDePadova);

        beachPlease.setStartDay(12);
        beachPlease.setEndDay(30);
        beachPlease.setStartMonth(6);
        beachPlease.setEndMonth(6);
        System.out.println(beachPlease);

        hydra.setAvgTourists(30);
        hydra.setMaxTourists(100);
        hydra.setStartHour(LocalTime.of(0,0),LocalTime.of(0,0),LocalTime.of(0,0),LocalTime.of(0,0),LocalTime.of(0,0),LocalTime.of(0,0),LocalTime.of(0,0));
        hydra.setEndHour(LocalTime.of(23,59),LocalTime.of(23,59),LocalTime.of(23,59),LocalTime.of(23,59),LocalTime.of(23,59),LocalTime.of(23,59),LocalTime.of(23,59));
        hydra.setVisitDays(1,2,3,4,5,6,7);
        System.out.println(hydra);
        aroundEurope.setStartDay(1);
        aroundEurope.setStartMonth(5);
        aroundEurope.setStartYear(2024);
        aroundEurope.setEndDay(30);
        aroundEurope.setEndMonth(5);
        aroundEurope.setEndYear(2024);
        System.out.println(aroundEurope);
    }
}