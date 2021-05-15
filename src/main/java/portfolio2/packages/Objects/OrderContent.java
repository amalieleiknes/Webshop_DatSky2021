package portfolio2.packages.Objects;

public class OrderContent {
    private OrderLine orderline;

    public OrderContent(OrderLine orderline) {
        this.orderline = orderline;
    }

    public OrderLine getOrderline() {
        return orderline;
    }

    public void setOrderline(OrderLine orderline) {
        this.orderline = orderline;
    }

    /*
    private List<Product> orderProductList;

    public OrderContent(String orderID, List<Product> orderProductList){
        this.orderID = orderID;
        this.orderProductList = orderProductList;
    }

    public String getOrderID(){
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<Product> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<Product> orderProductList) {
        this.orderProductList = orderProductList;
    }

     */
}
