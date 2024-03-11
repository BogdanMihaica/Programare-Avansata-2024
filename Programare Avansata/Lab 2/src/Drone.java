public class Drone extends Vehicle{
    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    private int flightDuration;
    public Drone(String name)
    {
        super(name);
    }
    public Drone(String name, int flightDuration) {
        super(name);
        this.flightDuration = flightDuration;
    }

    public Drone(String name, Depot depot, int flightDuration) {
        super(name, depot);
        this.flightDuration = flightDuration;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "flightDuration=" + flightDuration +
                " name=" + super.getName() +
                " depot=" + super.getDepot().getName()+
                '}';
    }
}
