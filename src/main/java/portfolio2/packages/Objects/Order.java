package portfolio2.packages.Objects;

public class Order {
    private String orderID;
    private Double totalPrice;
    private int amount;
    private int customerID;

    public Order(String orderID, Double totalPrice, int amount, int customerID) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.customerID = customerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
