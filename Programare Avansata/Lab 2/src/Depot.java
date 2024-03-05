import java.util.Arrays;

public class Depot {
    private String name;
    private Vehicle[] vehicles;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(Vehicle ... vehicles) {
        this.vehicles = vehicles;
        for(Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    public Depot(String name, Vehicle[] vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }



    public Depot(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public Depot(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }
}