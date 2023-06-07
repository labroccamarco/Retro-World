package entity;

public class Console {
    private int id;
    private String name;
    private String description;
    private Brand brand;

    public Console() {}

    public Console(int id, String name, String description, Brand brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
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

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Console [id=" + id + ", name=" + name + ", description=" + description + ", brand=" + brand + "]";
    }
}