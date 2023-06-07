package entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Order {
    private int id;
    private int userId;
    private Date date;
    private List<CartItem> carts;
    private double total;

    public Order() {
        this.carts = new ArrayList<>();
    }

    public Order(int userId, List<CartItem> carts, double total) {
        this.userId = userId;
        this.carts = carts;
        this.total = total;
    }

    public Order(int id, int userId, double total) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.date = new Date();
        this.carts = new ArrayList<>();
    }
    public Order(int id, int userId, Date date, List<CartItem> carts, double total) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.carts = carts;
        this.total = total;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CartItem> getCartItems() {
        return carts;
    }

    public void setCarts(List<CartItem> carts) {
        this.carts = carts;
    }

    public void addCart(CartItem cart) {
        this.carts.add(cart);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}