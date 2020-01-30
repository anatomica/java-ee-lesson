package service;

import store.Catalog;
import java.util.List;

public interface CatalogService {

    void insert(Catalog product);

    void update(Catalog product);

    void delete(long id);

    Catalog findById(long id);

    List<Catalog> findAll();


}
