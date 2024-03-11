import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class Compulsory {
    public static void main(String[] args) {
        Client andrei = new Client("Andrei", ClientType.PREMIUM);

        Depot garage = new Depot("Garaj Gara");

        andrei.setMinTime(LocalTime.NOON);
        andrei.setMaxTime(LocalTime.MIDNIGHT);

        System.out.println(andrei);

        System.out.println(garage);
    }
}