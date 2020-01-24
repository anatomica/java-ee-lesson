package store;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
@Named
public class CatalogRepository implements Serializable {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @PostConstruct
    public void init() {
        if (this.findAll().isEmpty()) {
            this.insert(new Catalog(-1L, "Product1", "Desc1", new BigDecimal(10)));
            this.insert(new Catalog(-1L, "Product2", "Desc2", new BigDecimal(20)));
            this.insert(new Catalog(-1L, "Product3", "Desc3", new BigDecimal(30)));
            this.insert(new Catalog(-1L, "Product4", "Desc4", new BigDecimal(40)));
        }
    }

    @Transactional
    public void insert(Catalog product) {
        em.persist(product);
    }

    @Transactional
    public void update(Catalog product) {
        em.merge(product);
    }

    @Transactional
    public void delete(long id) {
        Catalog catalog = em.find(Catalog.class, id);
        if (catalog != null) em.remove(catalog);
    }

    public Catalog findById(long id) {
        return em.find(Catalog.class, id);
    }

    public List<Catalog> findAll() {
        return em.createQuery("from Catalog ", Catalog.class).getResultList();
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
