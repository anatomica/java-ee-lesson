package store;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
public class CatalogRepositoryImpl implements CatalogRepository, CatalogRepositoryRemote, Serializable {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @PostConstruct
    public void init() {
        if (this.findAll().isEmpty()) {
            insert(new Catalog("Product1", "Desc1", new BigDecimal(10)));
            insert(new Catalog("Product2", "Desc2", new BigDecimal(20)));
            insert(new Catalog("Product3", "Desc3", new BigDecimal(30)));
            insert(new Catalog("Product4", "Desc4", new BigDecimal(40)));
        }
    }

    @TransactionAttribute
    @Override
    public void insert(Catalog product) {
        em.persist(product);
    }

    @TransactionAttribute
    @Override
    public void update(Catalog product) {
        em.merge(product);
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        Catalog catalog = em.find(Catalog.class, id);
        if (catalog != null) em.remove(catalog);
    }

    @Override
    public Catalog findById(long id) {
        return em.find(Catalog.class, id);
    }

    @Override
    public List<Catalog> findAll() {
        return em.createQuery("from Catalog ", Catalog.class).getResultList();
    }

    @Asynchronous
    @Override
    public Future<List<Catalog>> findAllAsync() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(em.createQuery("from Catalog ", Catalog.class).getResultList());
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
