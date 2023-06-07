package entity;
import java.awt.image.BufferedImage;


public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private Brand brand;
    private Brand console;
    private int amount;
    private String tag;
    private String type;
    private BufferedImage image;
    

    public Product(int id, String name, double price, String description, Brand brand, Brand console, int amount, String tag, String type,BufferedImage image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.console = console;
        this.amount = amount;
        this.tag = tag;
        this.type = type;
        this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void getType(String type){
        this.type = type;
    }
    public String setType(){
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getConsole() {
        return console;
    }

    public void setConsole(Brand console) {
        this.console = console;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}