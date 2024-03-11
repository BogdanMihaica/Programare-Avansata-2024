public class Truck extends Vehicle{
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public Truck(String name)
    {
        super(name);
    }
    public Truck(String name, int maxCapacity) {
        super(name);
        this.maxCapacity = maxCapacity;
    }

    public Truck(String name, Depot depot, int maxCapacity) {
        super(name, depot);
        this.maxCapacity = maxCapacity;
    }
    @Override
    public String toString() {
        return "Truck{" +
                "maxCapacity=" + maxCapacity +
                " name=" + super.getName() +
                " depot=" + super.getDepot().getName()+
                '}';
    }
    private int maxCapacity;
}
