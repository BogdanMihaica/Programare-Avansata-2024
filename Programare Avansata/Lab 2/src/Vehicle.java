import java.util.List;
import java.util.Objects;
import java.util.Vector;

public abstract class Vehicle {

    private Vector<Client> clients;
    private String name;
    private Depot depot;
    public String getName() {
        return name;
    }
    public void addClient(Client c)
    {
        clients.add(c);
    }
    public void setName(String name) {
        this.name = name;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }


    public Vehicle(String name) {
        clients = new Vector<>();
        this.name = name;
    }
    public Vehicle(String name, Depot depot) {
        clients = new Vector<>();
        this.name = name;
        this.depot = depot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depot);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", depot=" + depot.getName() +
                '}';
    }
}