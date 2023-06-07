package dao;

import entity.Order;
import entity.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveOrder(Order order) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            // Insert order
            stmt = connection.prepareStatement("INSERT INTO `order` (user_id, order_date, total_price) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, order.getUserId());
            stmt.setTimestamp(2, new Timestamp(order.getDate().getTime()));
            stmt.setDouble(3, order.getTotal());
            stmt.executeUpdate();

            // Get auto-generated order ID
            rs = stmt.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                connection.rollback();
                throw new SQLException("Failed to get auto-generated order ID.");
            }

            // Insert order carts
            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO cart_item (order_id, product_id, quantity) VALUES (?, ?, ?)");
            for (CartItem cartItem : order.getCartItems()) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, cartItem.getProductId());
                stmt.setInt(3, cartItem.getQuantity());
                stmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            connection.setAutoCommit(true);
        }
    }

    public List<Order> getOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM `order`");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                java.util.Date orderDate = new java.util.Date(rs.getTimestamp("order_date").getTime());
                double totalPrice = rs.getDouble("total_price");
                List<CartItem> cartItems = getOrderCartItems(id);
                Order order = new Order(id, userId, orderDate, cartItems, totalPrice);
                orders.add(order);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return orders;
    }

    private List<CartItem> getOrderCartItems(int orderId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM cart_item WHERE order_id = ?");
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                CartItem cartItem = new CartItem(productId, quantity);
                cartItems.add(cartItem);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return cartItems;
    }
}
