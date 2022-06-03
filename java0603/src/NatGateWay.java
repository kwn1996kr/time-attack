public class NatGateWay implements GateWay{
    int id;
    Subnet subnet;

    public NatGateWay(Subnet subnet) {
        this.subnet = subnet;
    }

    public void send(){
        return;
    }
}
