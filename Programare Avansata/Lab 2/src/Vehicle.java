public class Vehicle {


    private String name;
    private Depot depot;
    public String getName() {
        return name;
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
        this.name = name;
    }
    public Vehicle(String name, Depot depot) {
        this.name = name;
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", depot=" + depot.getName() +
                '}';
    }
}