import java.util.Arrays;
import java.util.Objects;

public class Depot {
    private String name;
    private Vehicle[] vehicles;
    public String getName() {
        return name;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(Vehicle ... vehicles) {
        Vehicle[] aux = vehicles;
        boolean ok=true;
        for(int i=0; i<aux.length-1; i++)
        {
            for(int j=i+1; j<aux.length; j++)
            {
                if(aux[i].equals(aux[j]))
                {
                    ok=false;
                    break;
                }
            }
        }
        if(!ok)
        {
            System.err.println("Error: Cannot add the same vehicle twice!");
        }
        else
        {
            this.vehicles = vehicles;
            for(Vehicle v : vehicles) {
                v.setDepot(this);
             }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(name, depot.name) && Arrays.equals(vehicles, depot.vehicles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }
}