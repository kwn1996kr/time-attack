import java.util.LinkedList;
import java.util.List;

public class RouteTable {
    int id;
    private GateWay gateWay;

    public void setGateWay(GateWay gateWay) {
        this.gateWay = gateWay;
    }

    List<Subnet> list = new LinkedList<>();

    public void addSubnet(Subnet subnet){
        list.add(subnet);
    }

    public void route(){
        for (Subnet subnet : list) {
        }
    }

}
