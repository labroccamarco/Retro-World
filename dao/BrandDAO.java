package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Brand;
import entity.Console;

public class BrandDAO {

    private Connection connection;

    public BrandDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Brand brand) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO brand (name) VALUES (?)");
            stmt.setString(1, brand.getName());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void update(Brand brand) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE brand SET name = ? WHERE id = ?");
            stmt.setString(1, brand.getName());
            stmt.setInt(2, brand.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Brand brand) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM brand WHERE id = ?");
            stmt.setInt(1, brand.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public List<Brand> findAll() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT id, name FROM brand");
            rs = stmt.executeQuery();
            List<Brand> brands = new ArrayList<>();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                findConsolesByBrand(brand.getId(), brand); // recupera le console associate al marchio e le aggiunge all'oggetto Brand
                brands.add(brand);
            }
            return brands;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public Brand findById(int id) throws SQLException {
        List<Brand> brands = new ArrayList<>(); // dichiarazione e inizializzazione della lista
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT name FROM brand WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Brand brand = new Brand();
                brand.setId(id);
                brand.setName(rs.getString("name"));
                brands.add(brand); // aggiunta del brand alla lista
                findConsolesByBrand(id, brand);
                return brand;
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
    
    private void findConsolesByBrand(int brandId, Brand brand) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT id, name, description FROM console WHERE brand_id = ?");
            stmt.setInt(1, brandId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Console console = new Console();
                console.setId(rs.getInt("id"));
                console.setName(rs.getString("name"));
                console.setDescription(rs.getString("description"));
                brand.addConsole(console);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /*public List<Brand> findAll() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT id, name FROM brand");
            rs = stmt.executeQuery();
            List<Brand> brands = new ArrayList<>();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                brand.addConsole(findConsolesByBrand(brand));
                brands.add(brand);
            }
            return brands;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }*/
    
}