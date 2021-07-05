package store;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@ApplicationScoped
@Named
public class CatalogRepository implements Serializable {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        if (this.findAll().isEmpty()) {
            try {
                userTransaction.begin();
                insert(new Catalog("Product1", "Desc1", new BigDecimal(10)));
                insert(new Catalog("Product2", "Desc2", new BigDecimal(20)));
                insert(new Catalog("Product3", "Desc3", new BigDecimal(30)));
                insert(new Catalog("Product4", "Desc4", new BigDecimal(40)));
                userTransaction.commit();
            } catch (Exception e) {
                userTransaction.rollback();
            }
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
