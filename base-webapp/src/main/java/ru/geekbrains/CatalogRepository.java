package ru.geekbrains;

import store.Catalog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogRepository {

    private final Connection conn;

    public CatalogRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public void insert(Catalog product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into products(`name`, `description`, `price`) values (?, ?, ?);")) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.execute();
        }
    }

    public void update(Catalog product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update products set `name` = ?, `description` = ?, `price` = ? where `id` = ?;")) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setLong(4, product.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from products where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Catalog findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select `id`, `name`, `description`, `price` from `products` where `id` = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Catalog(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4));
            }
        }
        return new Catalog(-1L, "", "", null);
    }

    public List<Catalog> findAll() throws SQLException {
        List<Catalog> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select `id`, `name`, `description`, `price` from `products`");

            while (rs.next()) {
                res.add(new Catalog(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists `products` (\n" +
                    "    `id` int auto_increment primary key,\n" +
                    "    `name` varchar(25),\n" +
                    "    `description` varchar(25),\n" +
                    "    `price` decimal(19, 2) \n" +
                    ");");
        }
    }
}
