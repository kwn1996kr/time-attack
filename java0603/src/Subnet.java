public class Subnet {
    int id;
    String locate;
    String ip;
    RouteTable routeTable;

    public Subnet(int id,String ip,String locate){
        this.id = id;
        this.ip = ip;
        this.locate = locate;
    }

    public void setRouteTable(RouteTable routeTable) {
        this.routeTable = routeTable;
    }

    public void transfer(String msg){
        System.out.println(msg);
    }
}
