package store;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CatalogRepository {

    void insert(Catalog product);

    void update(Catalog product);

    void delete(long id);

    Catalog findById(long id);

    List<Catalog> findAll();
}
