package portfolio2.packages.Objects;

public class Product {
    private int productID;
    private String productName;
    private String shortDescription;
    private String longDescription;
    private double price;
    private String imageURL;

    //POJO
    public Product(){}

    public Product(int productID, String productName, String shortDescription, String longDescription, double price, String imageURL) {
        this.productID = productID;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        if(imageURL == null){
            this.imageURL = "static/images/kaffe.jpg";
        }else {
            this.imageURL = imageURL;
        }

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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
