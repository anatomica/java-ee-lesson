package store;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class CatalogRepository {

    private Connection conn;

    @Inject
    private ServletContext servletContext;

    @PostConstruct
    public void init() throws SQLException {
        this.conn = (Connection) servletContext.getAttribute("connection");
        createTableIfNotExists(conn);

        if (this.findAll().isEmpty()) {
            this.insert(new Catalog(-1L, "Product1", "Desc1", new BigDecimal(10)));
            this.insert(new Catalog(-1L, "Product2", "Desc2", new BigDecimal(20)));
            this.insert(new Catalog(-1L, "Product3", "Desc3", new BigDecimal(30)));
            this.insert(new Catalog(-1L, "Product4", "Desc4", new BigDecimal(40)));
        }
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

    public List<Catalog> findChosen(List<Catalog> res, ArrayList<Integer> nums) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select `id`, `name`, `description`, `price` from `products`");

            while (rs.next()) {
                for (Integer num : nums) {
                    if (rs.getString(1).equals(String.valueOf(num)))
                        res.add(new Catalog(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4)));
                }
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
