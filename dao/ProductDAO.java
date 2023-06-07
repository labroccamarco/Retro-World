package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Brand;
import entity.Product;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT p.id, p.name, p.price, p.description, p.amount, p.tag,p.type, p.type, p.image, b.id AS brand_id, b.name AS brand_name, c.id AS console_id, c.name AS console_name, c.description AS console_description FROM product p INNER JOIN brand b ON p.brand_id = b.id LEFT JOIN brand c ON p.console_id = c.id");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("name");
                double productPrice = rs.getDouble("price");
                String productDescription = rs.getString("description");
                int productAmount = rs.getInt("amount");
                String productTag = rs.getString("tag");
                String productType = rs.getString("type");
                BufferedImage productImage = ImageIO.read(rs.getBinaryStream("image"));

                Brand brand = new Brand(rs.getInt("brand_id"), rs.getString("brand_name"));
                Brand console = null;
                int consoleId = rs.getInt("console_id");
                if (consoleId != 0) {
                    console = new Brand(rs.getInt("console_id"), rs.getString("console_name"));
                }

                Product product = new Product(productId, productName, productPrice, productDescription, brand, console, productAmount, productTag,productType, productImage);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}