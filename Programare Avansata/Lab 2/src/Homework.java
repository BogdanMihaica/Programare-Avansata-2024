
public class Homework {
    public static void main(String[] args)
    {
        Problem P = new Problem();
        Depot iasi = new Depot("Iasi");
        Client andrei = new Client("andrei");
        Client alex= new Client("alex");
        Client bogdan = new Client("bogdan");
        Depot bacau=new Depot("Bacau");
        Drone drona = new Drone("drona");
        bacau.setVehicles(drona);
        Truck man= new Truck("MAN");
        iasi.setVehicles(man);
        P.add(iasi);
        P.add(andrei);
        P.add(alex);
        P.add(bogdan);
        P.add(bacau);
        P.add(man);
        P.add(drona);
        P.allocateClients();


    }
}
