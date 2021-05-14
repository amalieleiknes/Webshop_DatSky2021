package portfolio2.packages.Objects;

import java.util.List;

public class OrderContent {
    private String orderID;
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
}
