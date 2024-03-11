import java.util.Random;
import java.util.Vector;

public class Problem {
    int[][] dtc;
    int[][] ctc;
    private Vector<Depot> depots;
    private Vector<Client> clients;
    private Vector<Vehicle> vehicles;

    public void add(Vehicle v)
    {
        boolean shouldAdd=true;
        for(Object obj : vehicles)
        {
            if(obj.equals(v))
            {
                shouldAdd=false;
                break;
            }
        }
        if(shouldAdd)
        vehicles.add(v);
        else{
            System.err.println("You can't add the same vehicle twice.");
        }
    }

    /**
     * Greedy algorithm to allocate clients to vehicles:
     * I chose arbitrarily 4 clients per vehicle and I allocated as many clients as I could
     * to the smallest number of vehicles possible.
     *
     */
    public void allocateClients()
    {
        int k=0;
        int remainingClients=clients.size();
        for(Vehicle v : vehicles)
        {
            int i=1;
            while(i<=4)
            {
                if(remainingClients>0)
                {
                    remainingClients--;
                }
                i++;
            }
            if(remainingClients==0)
            {
                break;
            }
        }
    }

    /**
     * To generate the costs for the client-to-client and depot-to-client tables I used the Random object to randomly
     * assign numbers as the time to travel from a destination to another
     */
    public void generateCosts()
    {
        ctc = new int[clients.size()][clients.size()];
        dtc = new int[depots.size()][clients.size()];
        Random nr= new Random();
        int cs=clients.size();
        int ds = depots.size();
        for(int i=0; i<cs; i++)
        {

            for(int j=0; j<cs; j++)
            {
                ctc[i][j]=1+nr.nextInt(500);
            }
            for(int j=0; j<ds; j++)
            {
                dtc[j][i]=1+nr.nextInt(500);
            }
        }
        for(int i=0; i<cs; i++)
        {
            for(int j=0; j<cs; j++)
            {
                System.out.print(ctc[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<ds; i++)
        {
            for(int j=0; j<cs; j++)
            {
                System.out.print(dtc[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param v
     * To make sure that I didn't already add a Depot/Client/Vehicle with the same name I ran a simple verification
     * using a boolean that flags when I add an Object of the same type twice in its corresponding array and if so I throw an error
     */
    public void add(Depot v)
    {
        boolean shouldAdd=true;
        for(Object obj : depots)
        {
            if(obj.equals(v))
            {
                shouldAdd=false;
                break;
            }
        }
        if(shouldAdd)
            depots.add(v);
        else{
            System.err.println("You can't add the same Depot twice.");
        }
    }
    public void add(Client v)
    {
        boolean shouldAdd=true;
        for(Object obj : clients)
        {
            if(obj.equals(v))
            {
                shouldAdd=false;
                break;
            }
        }
        if(shouldAdd)
            clients.add(v);
        else{
            System.err.println("You can't add the same client twice.");
        }
    }

    /**
     *
     * @return
     * For the getVehicles functions I created an array called toReturn, and then I went through all depots to get their Vehicles.
     * For each vehicle that I find, I add it in the toReturn array and I return it at the end.
     */
    public Vehicle[] getVehicles()
    {
        Vehicle[] toReturn= new Vehicle[vehicles.size()+1];
        int i=0;
        for(Depot d : depots)
        {
            Vehicle[] aux = d.getVehicles();
            if(aux!=null)
            {
                for(Vehicle v : aux)
                {
                    toReturn[i++]=v;
                }
            }

        }
        return toReturn;
    }

    public Vector<Depot> getDepots() {
        return depots;
    }

    public Vector<Client> getClients() {
        return clients;
    }
    public Problem()
    {
        vehicles=new Vector<Vehicle>();
        depots = new Vector<Depot>();
        clients= new Vector<Client>();
    }
}
