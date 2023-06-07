package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Console;
import entity.Brand;

public class ConsoleDAO {

    private Connection connection;

    public ConsoleDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Console console) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO console (name, description, brand_id) VALUES (?, ?, ?)");
            stmt.setString(1, console.getName());
            stmt.setString(2, console.getDescription());
            stmt.setInt(3, console.getBrand().getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void update(Console console) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE console SET name = ?, description = ?, brand_id = ? WHERE id = ?");
            stmt.setString(1, console.getName());
            stmt.setString(2, console.getDescription());
            stmt.setInt(3, console.getBrand().getId());
            stmt.setInt(4, console.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Console console) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM console WHERE id = ?");
            stmt.setInt(1, console.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public Console findById(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT name, description, brand_id FROM console WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Brand brand = new BrandDAO(connection).findById(rs.getInt("brand_id"));
                Console console = new Console();
                console.setId(id);
                console.setName(rs.getString("name"));
                console.setDescription(rs.getString("description"));
                console.setBrand(brand);
                return console;
            }
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

    public List<Console> findByBrand(Brand brand) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT id, name, description FROM console WHERE brand_id = ?");
            stmt.setInt(1, brand.getId());
            rs = stmt.executeQuery();
            List<Console> consoles = new ArrayList<>();
            while (rs.next()) {
                Console console = new Console();
                console.setId(rs.getInt("id"));
                console.setName(rs.getString("name"));
                console.setDescription(rs.getString("description"));
                console.setBrand(brand);
                consoles.add(console);
            }
            return consoles;
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
