package main.najah.code;

public class Product {
    private String name;
    private double price;
    private double discount = 0;

    public Product(String name, double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be non-negative");
        this.name = name;
        this.price = price;
    }

    public void applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 50) {
            throw new IllegalArgumentException("Invalid discount");
        }
        this.discount = discountPercentage;
    }

    public double getFinalPrice() {
        return price * (1 - discount / 100);
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDiscount() { return discount; }
}
