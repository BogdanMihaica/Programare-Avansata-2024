import java.time.LocalTime;
enum ClientType{
    REGULAR,
    PREMIUM
};
public class Client {
    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    public Client() {
        type=ClientType.REGULAR;
        this.name="Anonymous";
        minTime=null;
        maxTime=null;
    }
    public Client(String name, LocalTime minTime, LocalTime maxTime) {
        this.name = name;
        this.minTime=minTime;
        this.maxTime=maxTime;
        this.type=ClientType.REGULAR;
    }
    public Client(String name, LocalTime minTime, LocalTime maxTime, ClientType type) {
        this.name = name;
        this.minTime=minTime;
        this.maxTime=maxTime;
        this.type=type;
    }
    public Client(String name) {
        type=ClientType.REGULAR;
        this.name=name;
        minTime=null;
        maxTime=null;
    }
    public Client(String name, ClientType type)
    {
        this.name=name;
        this.type=type;
        minTime=null;
        maxTime=null;
    }

    ClientType type;
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;


    @Override
    public String toString() {
        return "Client{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }
}