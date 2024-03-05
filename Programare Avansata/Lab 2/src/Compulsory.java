import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class Compulsory {
    public static void main(String[] args) {
        Client andrei = new Client("Andrei", ClientType.PREMIUM);
        Vehicle audi = new Vehicle("Audi");
        Depot garage = new Depot("Garaj Gara");
        garage.setVehicles(audi);
        andrei.setMinTime(LocalTime.NOON);
        andrei.setMaxTime(LocalTime.MIDNIGHT);

        System.out.println(andrei);
        System.out.println(audi);
        System.out.println(garage);
    }
}