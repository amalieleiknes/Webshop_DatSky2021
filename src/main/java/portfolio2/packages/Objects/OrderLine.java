package portfolio2.packages.Objects;

public class OrderLine {
    private String orderID;
    private int productID;
    private String productName;
    private Double price;

    public OrderLine(String orderID, int productID, String productName, Double price) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
